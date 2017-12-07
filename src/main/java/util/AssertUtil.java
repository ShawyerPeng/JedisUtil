package util;

import exception.RedisClientException;
import org.apache.commons.lang3.StringUtils;

/**
 * 断言工具类
 */
public class AssertUtil {
    public static void notNull(Object object, String message) {
        if (null == object) {
            throw new RedisClientException(message);
        }
    }

    public static boolean notBlank(CharSequence cs, String message) {
        if (StringUtils.isBlank(cs)) {
            throw new RedisClientException(message);
        }
        return true;
    }

    public static void isTrue(boolean isTrue, String message) {
        if (!isTrue) {
            throw new RedisClientException(message);
        }
    }
}