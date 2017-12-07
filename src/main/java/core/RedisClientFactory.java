package core;

import config.RedisConfig;
import config.RedisConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RedisClient 对象生成工厂类
 */
public class RedisClientFactory {
    private static Logger logger = LoggerFactory.getLogger(RedisClientFactory.class);

    private static Map<String, RedisClient> redisClientMap = new ConcurrentHashMap<>();

    /**
     * 获取默认 RedisConfig 配置的 RedisClient 对象
     */
    public static RedisClient getClient() {
        return getClient(null);
    }

    public static RedisClient getClient(RedisConfig redisConfig) {
        RedisClient redisClient = null;
        String redisConfigFileName = RedisConstants.DEFALUT_REDIS_FILE_NAME;

        // 获取默认 RedisConfig 配置的 RedisClient 对象，并缓存到 redisClientMap 对象中
        if (null == redisConfig) {
            redisClient = redisClientMap.get(redisConfigFileName);
            if (redisClient == null) {
                redisClient = new JedisRedisClient().setRedisConfig(null);
                redisClientMap.put(redisConfigFileName, redisClient);
            }
            return redisClient;
        }

        // 获取自定义的 RedisConfig 配置的 RedisClient 对象，并缓存到 redisClientMap 对象中
        redisConfigFileName = redisConfig.getConfigFile();
        redisClient = redisClientMap.get(redisConfigFileName);
        if (redisClient == null) {
            redisClient = new JedisRedisClient().setRedisConfig(redisConfig);
            redisClientMap.put(redisConfigFileName, redisClient);
        }
        return redisClient;
    }
}
