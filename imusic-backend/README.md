# iMusic

一个基于 Spring Boot 的音乐流媒体和播放列表管理服务。

## 项目简介

iMusic 是一个功能完整的音乐流媒体应用，提供用户认证、歌曲搜索、播放列表管理等功能。支持传统密码登录和 OAuth2 第三方认证（Codeforces）。

## 主要功能

- **用户管理**
  - 密码登录和注册
  - OAuth2 第三方认证（Codeforces）
  - 会话管理（5小时超时）

- **歌曲管理**
  - 全局歌曲搜索（按标题、艺术家、专辑搜索）
  - 歌曲播放
  - 完整的歌曲元数据（标题、艺术家、专辑、年份、流派等）

- **播放列表管理**
  - 创建个人播放列表
  - 添加/删除播放列表中的歌曲
  - 在播放列表内搜索歌曲

## 技术栈

- **后端框架**: Spring Boot 3.5.6
- **编程语言**: Java 17
- **构建工具**: Maven
- **数据库**:
  - 开发环境：H2 Database（基于文件）
  - 生产环境：MySQL（可配置）
- **ORM**: Spring Data JPA / Hibernate
- **安全**: Spring Security
- **其他**: Jackson (JSON处理), OAuth 2.0


## API 接口

### 用户相关

| 方法 | 路径 | 描述 |
|------|------|------|
| POST | `/api/user/login/password` | 密码登录 |
| POST | `/api/user/register` | 用户注册 |
| GET | `/api/user/certificate/codeforces/` | Codeforces OAuth 回调 |
| POST | `/api/user/songLists/show` | 获取用户播放列表 |
| POST | `/api/user/songLists/add` | 创建新播放列表 |

### 歌曲相关

| 方法 | 路径 | 描述 |
|------|------|------|
| POST | `/api/song/search/all` | 搜索所有歌曲 |
| POST | `/api/song/play` | 获取歌曲播放详情 |
| POST | `/api/song/search/insonglist` | 在播放列表中搜索 |
| POST | `/api/song/add/tosonglist` | 添加歌曲到播放列表 |
| POST | `/api/song/delete/fromsonglist` | 从播放列表删除歌曲 |

### 静态资源

- 专辑封面: `/cover/**`
- 音频文件: `/mp3/**`

## 项目结构

```
imusic/
├── src/main/
│   ├── java/xyz/mywebs/imusic/
│   │   ├── Config/              # 配置类（安全、CORS、资源处理）
│   │   ├── Controller/          # REST 控制器
│   │   ├── Dto/                 # 数据传输对象
│   │   ├── Entity/              # JPA 实体类
│   │   ├── Repository/          # 数据访问层
│   │   ├── Service/             # 业务逻辑层
│   │   └── ImusicApplication.java  # 应用入口
│   └── resources/
│       ├── application.properties  # 应用配置
│       ├── music_info.json         # 音乐库数据
│       ├── cover/                  # 专辑封面目录
│       ├── mp3/                    # 音频文件目录
│       └── static/                 # 静态资源
├── pom.xml                      # Maven 配置
└── README.md                    # 项目说明
```

## 配置说明

### 应用配置 (application.properties)

```properties
# 服务器端口
server.port=8080

# 数据库配置
spring.datasource.url=jdbc:h2:file:./data/imusic
spring.datasource.driver-class-name=org.h2.Driver

# JPA/Hibernate 配置
spring.jpa.hibernate.ddl-auto=update

# H2 控制台
spring.h2.console.enabled=true

# OAuth2 配置
oauth2.codeforces.client-id=your_client_id
oauth2.codeforces.client-secret=your_client_secret

# 会话配置
server.servlet.session.timeout=300m
server.servlet.session.cookie.secure=false
```

### CORS 配置

应用已配置 CORS 支持以下域名：
- https://myweb2025.xyz
- https://imusic.myweb2025.xyz
- https://*.vercel.app
- http://localhost:* (开发环境)

## 开发指南

### 添加新歌曲

1. 将音频文件放入 `src/main/resources/mp3/` 目录
2. 将封面图片放入 `src/main/resources/cover/` 目录
3. 在 `src/main/resources/music_info.json` 中添加歌曲元数据


## 网址
1. web: https://www.myweb2025.xyz/
2. frontend: https://github.com/MagicalFlames/imusic-frontend


