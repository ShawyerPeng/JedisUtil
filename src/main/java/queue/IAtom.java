package queue;

/**
 * 具有事务性的队列操作接口类
 */
public interface IAtom {
    /**
     * 原子性队列数据获取(与队列取数据时保持事务性操作的原子性方法)
     */
    boolean run(String message);
}