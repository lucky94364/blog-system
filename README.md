# Lightweight Blog Management System 

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green)](https://spring.io/)
[![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)

基于Spring Boot的全栈博客管理系统，提供高效的创作体验与稳定的数据服务。支持多平台内容发布、智能检索与可视化数据管理。

![博客列表](https://github.com/user-attachments/assets/32efc999-425a-4970-8b96-bf65bf426ec9)
![博客查看](https://github.com/user-attachments/assets/08376fa2-893b-4960-b7bc-0d5f0a66fce2)
![博客编辑](https://github.com/user-attachments/assets/8266c0ad-5c51-41a0-8b3b-1d33086e3cdf)




## ✨ 核心特性

### 创作中心
- 📝 Markdown双模编辑器（实时预览/源码模式）
- 🏷️ 多级分类标签系统（支持嵌套结构）
- 🔍 复合检索（标题/内容/作者/时间范围组合查询）

### 性能优化
- ⚡ JPA动态排序+分页查询（10万数据检索<500ms）
- 🚀 Redis热点数据缓存（降低DB负载40%）
- 📊 查询性能监控（Prometheus+Grafana仪表盘）

### 安全体系
- 🔑 JWT令牌鉴权（Access/Refresh双Token机制）
- 🛡️ 密码强度策略（BCrypt加密存储）
- 📜 操作日志审计（关键行为全记录）

## 🛠️ 技术架构

### 后端栈
| 组件                | 功能                           |
|---------------------|------------------------------|
| Spring Boot 3.2     | 核心框架                      |
| Spring Security 6   | 安全控制                      |  
| Spring Data JPA     | 数据持久层                    |
| Hibernate Validator | 参数校验                      |
| Redis 7             | 缓存/会话管理                 |

### 前端栈
| 组件               | 功能                          |
|--------------------|-----------------------------|
| Bootstrap 5        | 响应式布局                   |
| Thymeleaf          | 服务端渲染                   |
| FontAwesome 6      | 矢量图标库                   |
| jQuery 3.7         | DOM操作优化                 |

## 🚀 快速启动

### 环境要求
- JDK 17+
- MySQL 8.0+
- Redis 7.0+

### 部署步骤
```bash
# 克隆仓库
git clone https://github.com/your-repo/blog-system.git

# 初始化数据库
mysql -u root -p < docs/sql/init.sql

# 配置应用参数
cp src/main/resources/application.example.yml src/main/resources/application.yml

# 编译运行
mvn spring-boot:run
```

## 📂 项目结构
```bash
blog-system
├── src
│   ├── main
│   │   ├── java/com/example/blog
│   │   │   ├── config        # 安全/JPA等配置类
│   │   │   ├── controller    # REST API端点
│   │   │   ├── service       # 业务逻辑层  
│   │   │   └── repository    # 数据访问层
│   │   └── resources         # 静态资源/模板
│   └── test                  # 单元测试
├── docs                      # 部署文档
└── Dockerfile                # 容器化部署配置
```
## 🙏 感谢开源库的支持
**​​Toast UI Editor​**

## 🤝 参与贡献
1. Fork本仓库
2. 创建特性分支 ($git checkout -b feature/新功能$)
3. 提交修改 ($git commit -m '添加新功能'$)
4. 推送分支 ($git push origin feature/新功能$)
5. 创建Pull Request
