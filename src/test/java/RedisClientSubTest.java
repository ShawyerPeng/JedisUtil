import config.RedisConfig;
import core.RedisClient;
import core.RedisClientFactory;
import event.RedisListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redis 发布订阅 - 订阅者测试类
 */
public class RedisClientSubTest {
    private static Logger logger = LoggerFactory.getLogger(RedisClientSubTest.class);

    public static void main(String[] args) {
        logger.info("生成自定义 RedisConfig 配置的 RedisClient 对象.");
        RedisClient redisClient = RedisClientFactory.getClient(new RedisConfigTest());

        String channel = "send-email";
        redisClient.subscribe(channel, new RedisListener() {
            @Override
            public void onMessage(String channel, String message) {
                logger.info("Received message from the channel = [" + channel + "],the message = [" + message + "]");
            }
        });

        logger.info("Redis 的发布订阅模式中，消息是瞬时消费的，当订阅者出现中断时会存在消息丢失.");
    }
}
