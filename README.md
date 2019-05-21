# Jedis 客户端工具类

在 `resources` 文件夹下创建 `redis.properties/properties` 文件夹下创建 `redis.properties`

```
+ sre/main/resource
  - redis.properties
  + properties
    - redis.properties
```

e.g. redis.properties 文件内容

```properties
# Redis settings
redis.host=localhost
redis.port=6379
redis.password=****
redis.timeout=10000
redis.maxIdle=300
redis.maxTotal=600
# 毫秒
redis.maxWaitMillis=1000
redis.testOnBorrow=false
```