import core.RedisClient;
import core.RedisClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis Hash 测试类
 */
public class RedisClientHashTest {
    private static Logger logger = LoggerFactory.getLogger(RedisClientHashTest.class);

    public static void main(String[] args) {
        logger.info(" 生成自定义 RedisConfig 配置的 RedisClient 对象.");
        RedisClient redisClient = RedisClientFactory.getClient(new RedisConfigTest());

        String key = "root-key";
        Map<String, String> hashMap = new HashMap<String, String>() {
            {
                put("abc", "abc");
            }
        };
        int expiredSeconds = 5 * 60;// 5 分钟
        redisClient.hmset(key, hashMap, expiredSeconds);
        logger.info(" 设置 HASH 值 hashMap：KEY = [" + key + "],SIZE = [" + hashMap.size() + "]");

        String hashKey = "hash-key";
        String hashValue = "hash-value";
        redisClient.hset(key, hashKey, hashValue);

        logger.info(" 获取 KEY = [" + hashKey + "] 的值 , VALUE = [" + redisClient.hget(key, hashKey) + "]");

        Map<String, String> resultMap = redisClient.hgetAll(key);
        for (String resultKey : resultMap.keySet()) {
            logger.info(" 遍历 resultMap,KEY = [" + resultKey + "],VALUE = [" + resultMap.get(resultKey) + "]");
        }

    }
}
