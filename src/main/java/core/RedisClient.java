package core;

import config.RedisConfig;
import event.RedisListener;
import queue.IAtom;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 操作类接口定义
 */
public interface RedisClient<T> {
    /**
     * 设置 config.RedisConfig 配置
     */
    RedisClient<?> setRedisConfig(RedisConfig redisConfig);

    /**
     * 获取 redis 的操作对象
     */
    T getJedis();

    /**
     * Returns all the keys matching the glob-style pattern as space separated strings. For example
     * if you have in the database the keys "foo" and "foobar" the command "KEYS foo*" will return
     * "foo foobar".
     */
    Set<String> keys(String pattern);

    /**
     * <b> 按模式删除多个 KEY</b> <br/>
     * Delete value When the keys matching the glob-style pattern as space separated strings. For
     * example if you have in the database the keys "foo" and "foobar" the method
     * "deleteByPattern(foo*)" will delete "foo foobar".<br/>
     */
    Long deleteByPattern(String patternKey);

    /**
     * <b>String 字符串写操作 </b> <br/>
     * Set the string value as value of the key. The string can't be longer than 1073741824 bytes (1GB).<br/>
     */
    boolean set(String key, String value);

    /**
     * <b>String 字符串写操作 </b> <br/>
     * <p>
     * Set the string value as value of the key. The string can't be longer than 1073741824 bytes (1
     * GB).<br/>
     * Moreover,Set the expiredSeconds of the key.
     */
    boolean set(String key, String value, int expiredSeconds);

    /**
     * <b>String 字符串读操作 </b> <br/>
     * <p>
     * Get the value of the specified key. If the key does not exist null is returned. If the value
     * stored at key is not a string an error is returned because GET can only handle string values.
     */
    String get(String key);

    /**
     * <b>Hash 写操作 </b> <br/>
     * <br/>
     * <p>
     * Set the respective fields to the respective values. HMSET replaces old values with new
     * values.
     * <p>
     * If key does not exist, a new key holding a hash is created.
     */
    boolean hmset(String key, Map<String, String> hashMap);

    /**
     * <b>Hash 写操作 </b> <br/>
     * <br/>
     * <p>
     * Set the respective fields to the respective values. HMSET replaces old values with new
     * values.
     * <p>
     * If key does not exist, a new key holding a hash is created.
     */
    boolean hmset(String key, Map<String, String> hashMap, int expiredSeconds);

    /**
     * <b>Hash 读操作 </b> <br/>
     * <br/>
     * <p>
     * Return all the fields and associated values in a hash.
     */
    Map<String, String> hgetAll(String key);

    /**
     * <b>Hash 写操作 </b> <br/>
     * <br/>
     * <p>
     * Set the specified hash field to the specified value.
     * <p>
     * If key does not exist, a new key holding a hash is created.
     */
    long hset(String key, String hashKey, String hashValue);

    /**
     * <b>Hash 读操作 </b> <br/>
     * <br/>
     * <p>
     * If key holds a hash, retrieve the value associated to the specified field.
     * <p>
     * If the field is not found or the key does not exist, a special 'nil' value is returned.
     */
    String hget(String key, String hashKey);

    /**
     * <b>List 写操作 </b> <br/>
     * <br/>
     * <p>
     * Add the string value to the head (LPUSH) or tail (RPUSH) of the list stored at key. If the
     * key does not exist an empty list is created just before the append operation. If the key
     * exists but is not a List an error is returned.
     */
    boolean lpush(String key, String... values);

    /**
     * <b>List 写操作（先进先出，可用于队列）</b> <br/>
     * <br/>
     * <p>
     * Add the string value to the head (LPUSH) or tail (RPUSH) of the list stored at key. If the
     * key does not exist an empty list is created just before the append operation. If the key
     * exists but is not a List an error is returned.
     */
    boolean lpush(String key, int expiredSeconds, String... values);

    /**
     * <b>List 写操作 </b> <br/>
     * <br/>
     * <p>
     * Add the string value to the head (LPUSH) or tail (RPUSH) of the list stored at key. If the
     * key does not exist an empty list is created just before the append operation. If the key
     * exists but is not a List an error is returned. <br/>
     */
    boolean rpush(String key, String... values);

    /**
     * <b>List 写操作 </b> <br/>
     * <br/>
     * <p>
     * Add the string value to the head (LPUSH) or tail (RPUSH) of the list stored at key. If the
     * key does not exist an empty list is created just before the append operation. If the key
     * exists but is not a List an error is returned. <br/>
     */
    boolean rpush(String key, int expiredSeconds, String... values);

    /**
     * <b>List 读操作（先进后出读取 - 类似栈）</b> <br/>
     * <br/>
     * <p>
     * Atomically return and remove the first (LPOP) or last (RPOP) element of the list. For example
     * if the list contains the elements "a","b","c" LPOP will return "a" and the list will become
     * "b","c".<br/>
     * <br/>
     * <p>
     * If the key does not exist or the list is already empty the special value 'nil' is returned.
     */
    String lpop(String key);

    /**
     * <b>List 读操作（先进先出读取 - 类似队列）</b> <br/>
     * <p>
     * Atomically return and remove the first (LPOP) or last (RPOP) element of the list. For example
     * if the list contains the elements "a","b","c" RPOP will return "c" and the list will become
     * "a","b". <br/>
     * <br/>
     * <p>
     * If the key does not exist or the list is already empty the special value 'nil' is returned.
     * <br/>
     */
    String rpop(String key);

    /**
     * <b>List 读操作 (保证绝对的事务性)</b> <br/>
     * 先进先出 OR 先进后出 取决于数据打入时采用的是 lpush OR rpush
     */
    String popQueue(String key, IAtom atom);

    /**
     * <b>List 读操作 </b> <br/>
     * <p>
     * Return the specified elements of the list stored at the specified key. Start and end are
     * zero-based indexes. 0 is the first element of the list (the list head), 1 the next element
     * and so on.
     * <p>
     * For example LRANGE foobar 0 2 will return the first three elements of the list.
     * <p>
     * start and end can also be negative numbers indicating offsets from the end of the list. For
     * example -1 is the last element of the list, -2 the penultimate element and so on.
     */
    List<String> lrange(String key, long start, long end);

    /**
     * <b>setnx </b> <br/>
     * SETNX works exactly like SET with the only difference that if the key already exists no
     * operation is performed. SETNX actually means "SET if Not eXists". <br/>
     * <br/>
     * 1 if the key was set 0 if the key was not set
     */
    long setnx(String key, String value, int expiredSeconds);

    /**
     * <b> 发布订阅 </b> <br/>
     * 消息订阅
     */
    boolean subscribe(String channel, RedisListener redisListener);

    /**
     * <b> 发布订阅 </b> <br/>
     * 消息发送
     */
    void publish(String channel, String message);

    /**
     * <b>incr</b> <br/>
     * Increment the number stored at key by one. If the key does not exist or contains a value of a
     * wrong type, set the key to the value of "0" before to perform the increment operation.
     */
    long incr(String key);

    /**
     * <b>decr</b> <br/>
     * Decrement the number stored at key by one. If the key does not exist or contains a value of a
     * wrong type, set the key to the value of "0" before to perform the decrement operation.
     */
    long decr(String key);
}
