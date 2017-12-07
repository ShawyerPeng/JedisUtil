package event;

/**
 * 自定义的发布订阅接口类
 */
public interface RedisListener {
    /**
     * 订阅消息
     */
    void onMessage(String channel, String message);
}