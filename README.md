# redisDemo
## 项目简介
本项目是一款面向Redis新手的入门级学习Demo，聚焦Redis核心使用场景与关键配置要点，所有代码均经过实测可直接运行，且配套完整可调用的接口，最大程度简化操作流程，帮助新手快速掌握Redis的使用方式。

项目重点覆盖Redis基本数据结构操作，并针对`StringRedisTemplate`与`RedisTemplate`的核心差异（序列化配置）提供了清晰的配置示例：`StringRedisTemplate`无需额外序列化配置，而`RedisTemplate`因易与JDK默认格式冲突，需在`config`包中完成自定义序列化配置。整体Demo设计便捷化、简单化，开箱即可运行，是新手入门Redis的优质学习资源。

## 项目结构
```
redisDemo/
├── .idea/                # IDEA 编辑器配置目录（已通过.gitignore忽略核心配置文件）
├── src/                  # 核心源代码目录（项目核心）
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── redis/
│   │   │           ├── config/       # Redis序列化配置包（核心配置）
│   │   │           │   └── RedisConfig.java # 解决RedisTemplate序列化冲突问题
│   │   │           ├── controller/   # 接口层，提供可直接调用的Redis操作接口
│   │   │           ├── service/      # 业务逻辑层，封装Redis数据结构操作
│   │   │           ├── impl/         # 业务逻辑实现类
│   │   │           └── RedisDemoApplication.java # 项目启动类
│   │   └── resources/
│   │       ├── application.yml       # 项目配置文件（Redis连接信息等）
│   │       └── application.properties # 可选配置文件
│   └── test/                          # 测试目录，验证核心代码可用性
│       └── java/
│           └── com/
│               └── redis/             # 测试类，覆盖各Redis操作场景
└── pom.xml                # 项目依赖配置（Maven）
```

## 环境准备
### 前置依赖
1. 安装 Redis 服务：
   - 本地部署：参考 [Redis 官方文档](https://redis.io/docs/getting-started/installation/) 完成安装，确保 Redis 服务启动并可访问。
   - 远程 Redis：准备可用的 Redis 服务地址、端口、密码（如有）。
2. 开发环境：
   - JDK 8+
   - IDE（推荐 IntelliJ IDEA）
   - Maven 3.6+（或Gradle，根据项目配置调整）

### 项目依赖
核心Redis依赖示例（Maven）：
```xml
<!-- pom.xml 核心依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<!-- 其他基础依赖（如web、测试等） -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

## 核心配置说明
### 序列化配置（config包）
针对`RedisTemplate`与`StringRedisTemplate`的序列化差异，在`config/RedisConfig.java`中完成核心配置：
- `StringRedisTemplate`：默认采用String序列化方式，无需额外配置，可直接使用。
- `RedisTemplate`：因默认使用JDK序列化易导致格式冲突，需自定义序列化规则（如JSON序列化），配置后可避免数据格式异常问题。

## 快速开始
1. 克隆项目到本地：
   ```bash
   git clone [项目仓库地址]
   cd redisDemo
   ```
2. 配置 Redis 连接信息：
   在`src/main/resources/application.yml`中修改Redis连接参数：
   ```yaml
   spring:
     redis:
       host: 127.0.0.1 # Redis主机地址
       port: 6379      # Redis端口
       password:       # Redis密码（如有则填写）
       database: 0     # 数据库索引
   ```
3. 运行项目：
   - 直接启动`src/main/java/com/redis/RedisDemoApplication.java`类。
   - 项目启动后，可通过调用`controller`层接口验证Redis操作。
4. 验证功能：
   - 访问接口（如http://localhost:8080/redis/string/set）测试Redis数据结构操作。
   - 查看控制台日志或Redis客户端，确认数据操作结果符合预期。

## 核心功能示例
### 1. 基本数据结构操作
Demo覆盖Redis全量基础数据结构，均提供可运行的接口与实现：
- 字符串（String）：set/get/incr/decr等操作
- 哈希（Hash）：hset/hget/hmset/hgetAll等操作
- 列表（List）：lpush/rpush/lpop/rpop/lrange等操作
- 集合（Set）：sadd/smembers/srem/sismember等操作
- 有序集合（ZSet）：zadd/zrange/zscore/zrem等操作

### 2. 核心配置示例
`config/RedisConfig.java`中解决`RedisTemplate`序列化冲突的核心配置逻辑，确保非字符串类型数据存储/读取格式正常。

## 注意事项
1. `.idea` 目录为 IDEA 编辑器本地配置，已通过 `.gitignore` 忽略核心配置文件，无需提交到版本库。
2. 运行前确保 Redis 服务正常启动，且`application.yml`中连接参数（主机、端口、密码）配置正确。
3. 新手使用时，优先理解`StringRedisTemplate`与`RedisTemplate`的序列化差异，避免因配置缺失导致数据异常。
4. 所有接口均经过实测，可直接通过Postman、浏览器等工具调用，快速验证功能。

## 联系方式
如有问题或建议，请在仓库中打开 Issue，或联系仓库维护者 niajiege。




以下是将这些 Redis 相关接口请求信息整理后添加到 README.md 末尾的内容，你可以直接复制使用：
# Redis 接口请求说明
## 一、列表（List）操作
### POST 从列表右边插入新元素
- 请求地址：`/redis/list/push`
- 请求方式：POST
- 请求体参数：
  | 名称    | 类型   | 必选 | 说明 |
  |---------|--------|------|------|
  | listKey | string | 否   | 列表键 |
  | value   | string | 否   | 插入值 |
- 返回示例（200 OK）：
```json
{}
```

## 二、哈希（Hash）操作
### GET 设置 Hash 单个属性
- 请求地址：`/redis/hash/put`
- 请求方式：GET
- 请求参数（Query）：
  | 名称    | 类型   | 必选 | 说明 |
  |---------|--------|------|------|
  | hashKey | string | 是   | 哈希键 |
  | field   | string | 是   | 哈希字段 |
- 返回示例（200 OK）：
```json
{
  "code": 200,
  "msg": "Hash单个属性设置成功"
}
```

### GET 查询 Hash 单个属性
- 请求地址：`/redis/hash/get`
- 请求方式：GET
- 请求参数（Query）：
  | 名称    | 类型   | 必选 | 说明 |
  |---------|--------|------|------|
  | hashKey | string | 是   | 哈希键 |
  | field   | string | 是   | 哈希字段 |
- 返回示例（200 OK）：
```json
{
  "code": 200,
  "msg": "Hash单个属性查询成功",
  "value": {}
}
```

### GET 查询 Hash 所有属性
- 请求地址：`/redis/hash/getAll`
- 请求方式：GET
- 请求参数（Query）：
  | 名称    | 类型   | 必选 | 说明 |
  |---------|--------|------|------|
  | hashKey | string | 是   | 哈希键 |
- 返回示例（200 OK）：
```json
{
  "code": 200,
  "msg": "Hash单个属性查询成功",
  "value": {
    "{}": {}
  }
}
```

## 三、字符串（String）操作
### GET 设置字符串缓存
- 请求地址：`/redis-string/set`
- 请求方式：GET
- 请求参数（Query）：
  | 名称    | 类型    | 必选 | 说明 |
  |---------|---------|------|------|
  | key     | string  | 是   | 缓存键 |
  | value   | string  | 是   | 缓存值 |
  | timeout | integer | 是   | 过期时间（秒） |
- 返回示例（200 OK）：
```json
{
  "code": 200,
  "msg": "缓存设置成功"
}
```

### GET 查询字符串缓存
- 请求地址：`/redis-string/get`
- 请求方式：GET
- 请求参数（Query）：
  | 名称 | 类型   | 必选 | 说明 |
  |------|--------|------|------|
  | key  | string | 是   | 缓存键 |
- 返回示例（200 OK）：
```json
{
  "code": 200,
  "msg": "缓存查询成功===>"
}
```

## 数据模型说明
### MapObject
```json
{
  "code": 0,
  "msg": "string",
  "当前list元素个数:": 0
}
```
| 名称              | 类型    | 必选 | 说明 |
|-------------------|---------|------|------|
| code              | integer | 否   | 状态码 |
| msg               | string  | 否   | 提示信息 |
| 当前list元素个数: | integer | 否   | 列表元素数量 |


你可以将上述内容直接追加到现有 README.md 文件的末尾，内容做了结构化整理，保留了所有接口的核心信息（请求地址、方式、参数、返回示例），同时优化了格式可读性，符合 README 文档的常见书写规范。
