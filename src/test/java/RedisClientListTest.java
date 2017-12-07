import config.RedisConfig;
import core.RedisClient;
import core.RedisClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import queue.IAtom;

import java.util.List;

/**
 * Redis List 测试类
 */
public class RedisClientListTest {
    private static Logger logger = LoggerFactory.getLogger(RedisClientListTest.class);

    public static void main(String[] args) {
        logger.info("生成自定义 RedisConfig 配置的 RedisClient 对象");
        RedisClient redisClient = RedisClientFactory.getClient(new RedisConfigTest());

        String key = "list-key";
        String value = "list-value";
        int expireSeconds = 5 * 60;

        // 清除所有数据
        redisClient.deleteByPattern("*");
        for (int i = 0; i < 50; i++) {
            redisClient.lpush(key, expireSeconds, value + i);
        }

        // logger.info("从链表头部开始取出其中一个数据, VALUE = ["+redisClient.lpop(key)+"]");
        // logger.info("从链表尾部开始取出其中一个数据, VALUE = ["+redisClient.rpop(key)+"]");
        logger.info("以保证 redis List 取值的事务性，从队列中取出数据并消费，如果消费失败则撤回取数据操作");
        for (int i = 1; i < 51; i++) {
            logger.info("第 [" + i + "] 次取队列 KEY= [" + key + "] 中的数据");
            redisClient.popQueue(key, new IAtom() {
                @Override
                public boolean run(String message) {
                    logger.info("取出队列信息, VALUE = [" + message + "]");
                    return true;
                }
            });
        }

        List<String> resultList = redisClient.lrange(key, 0, 5);
        for (String resultValue : resultList) {
            logger.info("获取 List 的值, VALUE = [" + resultValue + "]");
        }
    }
}