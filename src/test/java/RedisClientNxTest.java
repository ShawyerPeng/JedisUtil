import core.RedisClient;
import core.RedisClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * setnx、hsetnx... 等操作测试类
 */
public class RedisClientNxTest {
    private static Logger logger = LoggerFactory.getLogger(RedisClientNxTest.class);

    public static void main(String[] args) {
        logger.info(" 生成自定义 RedisConfig 配置的 RedisClient 对象.");
        RedisClient redisClient = RedisClientFactory.getClient(new RedisConfigTest());

        String key = "setnx-key";
        String value = "setnx-value";
        int expireSeconds = 5 * 60;// 5 分钟
        long result = redisClient.setnx(key, value, expireSeconds);
        logger.info("[setnx] 第一次执行结果为：" + result);
        result = redisClient.setnx(key, value, expireSeconds);
        logger.info("[setnx] 第二次执行结果为：" + result);
        result = redisClient.setnx(key, value, expireSeconds);
        logger.info("[setnx] 第三次执行结果为：" + result);

        redisClient.deleteByPattern(key);
        logger.info("删除 KEY= [" + key + "]");
    }
}