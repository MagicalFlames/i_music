#!/bin/bash

# iMusic 微服务启动脚本

echo "=========================================="
echo "       iMusic 微服务启动脚本"
echo "=========================================="

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 项目根目录
PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"

# MySQL 配置
MYSQL_USER="imusic"
MYSQL_PASSWORD="imusic123"
MYSQL_ROOT_PASSWORD=""

# 获取 sudo 密码
get_sudo_password() {
    echo -e "${YELLOW}请输入 sudo 密码:${NC}"
    read -s SUDO_PASSWORD
    echo ""
}

# 检查 MySQL 是否运行
check_mysql() {
    echo -e "${YELLOW}[1/5] 检查 MySQL 服务...${NC}"
    if systemctl is-active --quiet mysql; then
        echo -e "${GREEN}  ✓ MySQL 服务正在运行${NC}"
    else
        echo -e "${YELLOW}  → 启动 MySQL 服务...${NC}"
        echo "$SUDO_PASSWORD" | sudo -S systemctl start mysql 2>/dev/null
        sleep 2
        if systemctl is-active --quiet mysql; then
            echo -e "${GREEN}  ✓ MySQL 服务已启动${NC}"
        else
            echo -e "${RED}  ✗ MySQL 服务启动失败${NC}"
            exit 1
        fi
    fi
}

# 创建数据库和用户
setup_database() {
    echo -e "${YELLOW}[2/5] 初始化数据库...${NC}"

    # 使用 sudo 执行 MySQL 命令创建数据库和用户
    echo "$SUDO_PASSWORD" | sudo -S mysql -e "
CREATE DATABASE IF NOT EXISTS imusic_user CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS imusic_music CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS imusic_playlist CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS '${MYSQL_USER}'@'localhost' IDENTIFIED BY '${MYSQL_PASSWORD}';
GRANT ALL PRIVILEGES ON imusic_user.* TO '${MYSQL_USER}'@'localhost';
GRANT ALL PRIVILEGES ON imusic_music.* TO '${MYSQL_USER}'@'localhost';
GRANT ALL PRIVILEGES ON imusic_playlist.* TO '${MYSQL_USER}'@'localhost';
FLUSH PRIVILEGES;
" 2>/dev/null

    if [ $? -eq 0 ]; then
        echo -e "${GREEN}  ✓ 数据库初始化完成${NC}"
        echo -e "${GREEN}    - imusic_user${NC}"
        echo -e "${GREEN}    - imusic_music${NC}"
        echo -e "${GREEN}    - imusic_playlist${NC}"
    else
        echo -e "${RED}  ✗ 数据库初始化失败${NC}"
        exit 1
    fi
}

# 停止已运行的服务
stop_services() {
    echo -e "${YELLOW}[3/6] 停止已运行的服务...${NC}"
    pkill -f "user-service" 2>/dev/null
    pkill -f "music-service" 2>/dev/null
    pkill -f "playlist-service" 2>/dev/null
    pkill -f "gateway-service" 2>/dev/null
    pkill -f "vite" 2>/dev/null
    pkill -f "imusic-frontend" 2>/dev/null
    sleep 2
    echo -e "${GREEN}  ✓ 已停止旧服务${NC}"
}

# 启动微服务
start_services() {
    echo -e "${YELLOW}[4/6] 启动后端微服务...${NC}"

    # 创建日志目录
    LOG_DIR="$PROJECT_DIR/logs"
    mkdir -p "$LOG_DIR"

    # 启动 user-service
    echo -e "  → 启动 user-service (8081)..."
    cd "$PROJECT_DIR/user-service"
    nohup mvn spring-boot:run > "$LOG_DIR/user-service.log" 2>&1 &
    sleep 8

    # 启动 music-service
    echo -e "  → 启动 music-service (8082)..."
    cd "$PROJECT_DIR/music-service"
    nohup mvn spring-boot:run > "$LOG_DIR/music-service.log" 2>&1 &
    sleep 8

    # 启动 playlist-service
    echo -e "  → 启动 playlist-service (8083)..."
    cd "$PROJECT_DIR/playlist-service"
    nohup mvn spring-boot:run > "$LOG_DIR/playlist-service.log" 2>&1 &
    sleep 8

    # 启动 gateway-service
    echo -e "  → 启动 gateway-service (8080)..."
    cd "$PROJECT_DIR/gateway-service"
    nohup mvn spring-boot:run > "$LOG_DIR/gateway-service.log" 2>&1 &
    sleep 8

    echo -e "${GREEN}  ✓ 后端服务已启动${NC}"
}

# 启动前端
start_frontend() {
    echo -e "${YELLOW}[5/6] 启动前端服务...${NC}"

    cd "$PROJECT_DIR/imusic-frontend"

    # 检查是否需要安装依赖
    if [ ! -d "node_modules" ]; then
        echo -e "  → 安装前端依赖..."
        npm install > "$LOG_DIR/frontend-install.log" 2>&1
    fi

    echo -e "  → 启动前端 (3000)..."
    nohup npm run dev > "$LOG_DIR/frontend.log" 2>&1 &
    sleep 5

    echo -e "${GREEN}  ✓ 前端服务已启动${NC}"
}

# 检查服务状态
check_services() {
    echo -e "${YELLOW}[6/6] 检查服务状态...${NC}"
    sleep 3

    # 检查后端服务
    services=("8081:user-service" "8082:music-service" "8083:playlist-service" "8080:gateway-service")
    all_ok=true

    for service in "${services[@]}"; do
        port="${service%%:*}"
        name="${service##*:}"
        if curl -s "http://localhost:$port" > /dev/null 2>&1 || curl -s "http://localhost:$port/api" > /dev/null 2>&1; then
            echo -e "${GREEN}  ✓ $name (端口 $port) - 运行中${NC}"
        else
            echo -e "${RED}  ✗ $name (端口 $port) - 未响应${NC}"
            all_ok=false
        fi
    done

    # 检查前端服务
    if curl -s "http://localhost:3000" > /dev/null 2>&1; then
        echo -e "${GREEN}  ✓ frontend (端口 3000) - 运行中${NC}"
    else
        echo -e "${RED}  ✗ frontend (端口 3000) - 未响应${NC}"
        all_ok=false
    fi

    echo ""
    if [ "$all_ok" = true ]; then
        echo -e "${GREEN}=========================================="
        echo -e "       所有服务启动成功!"
        echo -e "==========================================${NC}"
        echo ""
        echo "前端地址: http://localhost:3000"
        echo "API 网关: http://localhost:8080"
        echo ""
        echo "日志目录: $LOG_DIR/"
    else
        echo -e "${YELLOW}部分服务可能还在启动中，请查看日志文件${NC}"
        echo "日志目录: $LOG_DIR/"
    fi
}

# 主流程
main() {
    get_sudo_password
    check_mysql
    setup_database
    stop_services
    start_services
    start_frontend
    check_services
}

main
