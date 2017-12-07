import core.RedisClient;
import core.RedisClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Redis String 读写操作测试类
 */
public class RedisClientStringTest {
    private static Logger logger = LoggerFactory.getLogger(RedisClientStringTest.class);

    public static void main(String[] args) {
        System.out.println("");
        logger.info("生成默认 RedisConfig 配置的 RedisClient 对象.");
        RedisClient redisClient = RedisClientFactory.getClient();

        logger.info("生成自定义 RedisConfig 配置的 RedisClient 对象.");
        redisClient = RedisClientFactory.getClient(new RedisConfigTest());

        logger.info("获取到 Jedis 对象，方便 developers 自定义扩展 redis 的其他操作 ");
        Jedis jedis = (Jedis) redisClient.getJedis();
        jedis.close();

        logger.info("Redis Set 字符串英文值 ");
        redisClient.set("test1", "my name is cpthack .");

        logger.info("Redis Set 字符串中文值 ");
        redisClient.set("test2", " 我叫成佩涛 .");

        logger.info("获取 KEY = [test] 的值 VALUE = [" + redisClient.get("test") + "]");

        Set<String> keySet = redisClient.keys("t*");
        for (String key : keySet) {
            logger.info("遍历 redis 中的 key，目前查询到有 >>>>> KEY = [" + key + "]");
        }

        logger.info("删除所有模式匹配 [t*] 的 KEY, 总共删除 COUNT=[" + redisClient.deleteByPattern("t*") + "]");
        keySet = redisClient.keys("t*");
        logger.info("目前还剩符合模式匹配 [t*] 的 KEY，总共有 COUNT=[" + keySet.size() + "]");
    }
}