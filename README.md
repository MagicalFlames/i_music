# IMusic - 在线音乐播放系统

一个基于 React + Spring Boot 的现代化在线音乐播放系统，支持歌曲搜索、在线播放、用户登录和收藏管理等功能。

## 项目截图

### 主页面
![主页面](./docs/screenshots/main_page.png)

### 播放器界面
![播放器界面](./docs/screenshots/player_page.png)

### 登录界面
![登录界面](./docs/screenshots/login_page.png)

### 收藏页面
![收藏页面](./docs/screenshots/favorites_empty.png)

## 功能特性

- **用户系统**：支持用户注册、登录，登录状态持久化
- **歌曲搜索**：支持按歌曲名称或艺术家搜索
- **在线播放**：支持播放/暂停、上一曲/下一曲、进度控制、音量调节
- **收藏管理**：支持添加/移除收藏歌曲
- **自动播放**：播放完毕自动切换下一首

## 技术栈

### 前端
| 技术 | 版本 | 说明 |
|-----|------|-----|
| React | 19.2.0 | 前端 UI 框架 |
| Vite | 7.1.10 | 前端构建工具 |

### 后端
| 技术 | 版本 | 说明 |
|-----|------|-----|
| Java | 17 | 开发语言 |
| Spring Boot | 3.5.6 | 后端框架 |
| Spring Data JPA | - | 数据持久层 |
| Spring Security | - | 安全框架 |
| H2 Database | - | 嵌入式数据库 |

## 项目结构

```
imusic/
├── imusic-backend/          # 后端项目
│   ├── src/main/java/       # Java 源代码
│   │   └── xyz/mywebs/imusic/
│   │       ├── Controller/  # 控制器层
│   │       ├── Service/     # 服务层
│   │       ├── Repository/  # 数据访问层
│   │       ├── Entity/      # 实体类
│   │       ├── Dto/         # 数据传输对象
│   │       └── Config/      # 配置类
│   └── pom.xml
│
├── imusic-frontend/         # 前端项目
│   ├── src/
│   │   ├── components/      # React 组件
│   │   ├── App.jsx          # 主组件
│   │   └── config.js        # 配置文件
│   └── package.json
│
└── docs/                    # 文档和截图
    └── screenshots/
```

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- Node.js 18+

### 后端启动

```bash
# 进入后端目录
cd imusic-backend

# 启动服务
mvn spring-boot:run
```

后端服务将在 http://localhost:8080 启动

### 前端启动

```bash
# 进入前端目录
cd imusic-frontend

# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

前端服务将在 http://localhost:3000 启动

## API 接口

| 接口 | 方法 | 说明 |
|-----|------|-----|
| `/api/user/register` | POST | 用户注册 |
| `/api/user/login/password` | POST | 用户登录 |
| `/api/song/search/all` | POST | 搜索歌曲 |
| `/api/song/add/tosonglist` | POST | 添加到收藏 |
| `/api/song/delete/fromsonglist` | POST | 从收藏删除 |
| `/api/song/search/insonglist` | POST | 查询收藏列表 |

## 数据库设计

| 表名 | 说明 |
|-----|------|
| users | 用户表 |
| songs | 歌曲信息表 |
| song_list | 歌单表 |
| song_list_items | 歌单项目表 |

## 相关文档

- [系统说明文档](./系统说明文档.md)
- [开发日志](./开发日志.md)
- [系统使用说明](./系统使用说明.md)

## 开发者

邬子阳

## License

MIT License
