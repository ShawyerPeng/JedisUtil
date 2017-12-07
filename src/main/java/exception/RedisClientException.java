package exception;

/**
 * RedisClient 的自定义异常类
 */
public class RedisClientException extends ApplicationException {
    private static final long serialVersionUID = -873631143382257801L;

    public RedisClientException(Throwable t) {
        super(t);
    }

    public RedisClientException(String message) {
        super(message);
    }
}