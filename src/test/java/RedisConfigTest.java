import config.RedisConfig;

/**
 * 自定义 RedisConfig 配置测试类
 */
public class RedisConfigTest extends RedisConfig {
    private static final String FILE_NAME = "test_redis_config.properties";

    @Override
    public String getConfigFile() {
        return FILE_NAME;
    }
}