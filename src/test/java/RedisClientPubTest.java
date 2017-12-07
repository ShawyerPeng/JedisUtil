import core.RedisClient;
import core.RedisClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redis 发布订阅 - 发布者测试类
 */
public class RedisClientPubTest {
    private static Logger logger = LoggerFactory.getLogger(RedisClientPubTest.class);

    public static void main(String[] args) {
        logger.info("生成自定义 RedisConfig 配置的 RedisClient 对象.");
        RedisClient redisClient = RedisClientFactory.getClient(new RedisConfigTest());

        String channel = "send-email";

        for (int i = 0; i < 50000; i++)
            redisClient.publish(channel, "发布订阅，我是成佩涛，你收到信息了吗？");
    }
}