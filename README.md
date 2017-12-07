# 简介
基于 Jedis 客户端封装的 Redis 操作工具类，包含了常用的操作方法。

# 使用示例
1. 自定义 RedisConfig 配置  
继承 RedisConfig 类并且重写 getConfigFile() 方法即可，重新指定 Redis 的配置文件。
    ```java
    public class TestRedisConfig extends RedisConfig {
        private static final String FILE_NAME = "test_redis_config.properties";
    
        @Override
        public String getConfigFile() {
            return FILE_NAME;
        }
    }
    ```
    
2. 数据类型使用具体参考：  
* String 数据类型测试类：RedisClientStringTest.java
* Hash 数据类型测试类：RedisClientHashTest.java
* List 数据类型测试类：RedisClientListTest.java
* Nx 开头命令测试类：RedisClientNxTest.java
* 发布订阅 - 消息订阅者测试类：RedisClientSubTest.java
* 发布订阅 - 消息发布者测试类：RedisClientPubTest.java

# 参考资料
[cpthack/redis-client](https://github.com/cpthack/redis-client)  
