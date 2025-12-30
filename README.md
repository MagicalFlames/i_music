# iMusic - 在线音乐播放系统（微服务架构）

基于 Spring Boot 微服务 + React 的在线音乐播放系统，支持歌曲搜索、在线播放、用户登录和收藏管理。

## 技术栈

| 层级 | 技术 |
|-----|------|
| 前端 | React 19 + Vite 7 |
| 后端 | Spring Boot 3.2 + Spring Cloud Gateway |
| 数据库 | MySQL 8.0 |
| ORM | Spring Data JPA + Hibernate |

## 系统架构

```
                    ┌─────────────────┐
                    │   Frontend      │
                    │  (React:3000)   │
                    └────────┬────────┘
                             │
                    ┌────────▼────────┐
                    │  API Gateway    │
                    │    (:8080)      │
                    └────────┬────────┘
           ┌─────────────────┼─────────────────┐
           │                 │                 │
  ┌────────▼────────┐ ┌──────▼───────┐ ┌──────▼───────┐
  │  user-service   │ │music-service │ │playlist-svc  │
  │    (:8081)      │ │   (:8082)    │ │   (:8083)    │
  └────────┬────────┘ └──────┬───────┘ └──────┬───────┘
           │                 │                 │
  ┌────────▼────────┐ ┌──────▼───────┐ ┌──────▼───────┐
  │  imusic_user    │ │ imusic_music │ │imusic_playlist│
  │    (MySQL)      │ │   (MySQL)    │ │   (MySQL)    │
  └─────────────────┘ └──────────────┘ └──────────────┘
```

## 微服务说明

| 服务 | 端口 | 数据库 | 功能 |
|-----|------|--------|-----|
| gateway-service | 8080 | - | API 网关、路由转发、CORS |
| user-service | 8081 | imusic_user | 用户注册、登录、验证 |
| music-service | 8082 | imusic_music | 歌曲搜索、播放、资源服务 |
| playlist-service | 8083 | imusic_playlist | 歌单管理、收藏功能 |

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- Node.js 18+
- MySQL 8.0+

### 一键启动

```bash
# 启动所有服务（需要 sudo 密码启动 MySQL）
./start.sh

# 停止所有服务
./stop.sh
```

启动脚本会自动：
1. 检查并启动 MySQL 服务
2. 创建数据库和用户
3. 启动 4 个后端微服务
4. 启动前端开发服务器

### 手动启动

```bash
# 1. 启动后端微服务（按顺序）
cd user-service && mvn spring-boot:run &
cd music-service && mvn spring-boot:run &
cd playlist-service && mvn spring-boot:run &
cd gateway-service && mvn spring-boot:run &

# 2. 启动前端
cd imusic-frontend
npm install
npm run dev
```

### 访问地址

- 前端：http://localhost:3000
- API 网关：http://localhost:8080

## 项目结构

```
imusic_ms/
├── gateway-service/        # API 网关
├── user-service/           # 用户服务
├── music-service/          # 音乐服务
│   └── src/main/resources/
│       ├── music_info.json # 歌曲元数据
│       ├── mp3/            # 音频文件
│       └── cover/          # 封面图片
├── playlist-service/       # 歌单服务
├── imusic-frontend/        # React 前端
├── logs/                   # 服务日志
├── start.sh                # 启动脚本
├── stop.sh                 # 停止脚本
└── README.md
```

## API 接口

### 用户服务 (user-service)

| 方法 | 路径 | 说明 |
|-----|------|-----|
| POST | /api/user/register | 用户注册 |
| POST | /api/user/login/password | 用户登录 |
| POST | /api/user/validate | 验证用户 |

### 音乐服务 (music-service)

| 方法 | 路径 | 说明 |
|-----|------|-----|
| POST | /api/song/search/all | 搜索歌曲（空关键词返回全部） |
| POST | /api/song/play | 获取播放信息 |
| GET | /api/song/get | 获取歌曲详情 |
| GET | /cover/** | 封面图片资源 |
| GET | /mp3/** | 音频文件资源 |

### 歌单服务 (playlist-service)

| 方法 | 路径 | 说明 |
|-----|------|-----|
| POST | /api/user/songLists/show | 获取用户歌单 |
| POST | /api/user/songLists/add | 创建歌单 |
| POST | /api/song/search/insonglist | 查询歌单歌曲 |
| POST | /api/song/add/tosonglist | 添加到歌单 |
| POST | /api/song/delete/fromsonglist | 从歌单删除 |

### 认证方式

歌单相关接口需要在请求头中传递用户信息：

```
X-Username: 用户名
X-Password: 密码
```

## 数据库配置

MySQL 用户：`imusic` / `imusic123`

```sql
-- 数据库
imusic_user      -- 用户数据
imusic_music     -- 歌曲数据
imusic_playlist  -- 歌单数据
```

## 日志查看

```bash
# 查看各服务日志
tail -f logs/user-service.log
tail -f logs/music-service.log
tail -f logs/playlist-service.log
tail -f logs/gateway-service.log
tail -f logs/frontend.log
```

## 功能特性

- 歌曲搜索（支持按标题、艺术家搜索）
- 在线播放（支持进度控制、音量调节）
- 用户系统（注册、登录）
- 收藏管理（添加/移除收藏）
- 响应式设计（适配移动端）
