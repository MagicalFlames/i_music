#!/bin/bash

# iMusic 微服务停止脚本

echo "=========================================="
echo "       iMusic 微服务停止脚本"
echo "=========================================="

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${YELLOW}正在停止所有服务...${NC}"

# 停止后端服务
pkill -f "user-service" 2>/dev/null
pkill -f "music-service" 2>/dev/null
pkill -f "playlist-service" 2>/dev/null
pkill -f "gateway-service" 2>/dev/null

# 停止前端服务
pkill -f "vite" 2>/dev/null
pkill -f "node.*imusic-frontend" 2>/dev/null

sleep 2

echo -e "${GREEN}✓ 后端服务已停止${NC}"
echo -e "  - user-service (8081)"
echo -e "  - music-service (8082)"
echo -e "  - playlist-service (8083)"
echo -e "  - gateway-service (8080)"
echo -e "${GREEN}✓ 前端服务已停止${NC}"
echo -e "  - frontend (3000)"

echo ""
echo -e "${GREEN}所有服务已停止${NC}"
