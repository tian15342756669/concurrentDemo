package com.hispeed.test;

/**
 * Created by dengtianguang on 2018/9/4.
 */
public class VolatileFeaturesExample {

    /**
    使用volatile声明64位的long型变量
     */
    volatile long vl = 0L;

    /**
     * 单个volatile变量的写
     */
    public void set(long l) {
        vl = l;
    }

    /**
     * 复合（多个）volatile变量的读/写
     */
    public void getAndIncrement () {
        vl++;
    }

    /**
     * 单个volatile变量的读
     */
    public long get() {
        return vl;
    }

    //等价于VolatileFeaturesExample2.Class
}


/**
 * 监视器锁的happen-before规则保证释放监视器和获取监视器的两个线程之间的内存可见性，
 * 这意味着对一个volatile变量的读，总是能看到（任意线程）对这个volatile变量最后的写入。
 */


/**
 *
 * 监视器锁的语义决定了临界区代码的执行具有原子性。
 * 这意味着即使是64位的long型和double型变量，只要它是volatile变量，
 * 对该变 量的读写就将具有原子性。如果是多个volatile操作或类似于volatile++这种复合操作，
 * 这些操作整体上不具有原子性。
 */

/**
 * 可见性。对一个volatile变量的读，总是能看到（任意线程）对这个volatile变量最后的写入。
 原子性：对任意单个volatile变量的读/写具有原子性，但类似于volatile++这种复合操作不具有原子性。
 */
class VolatileFeaturesExample2 {
    // 64位的long型普通变量
    long vl = 0L;

    public synchronized void set(long l) {     //对单个的普通 变量的写用同一个监视器同步
        vl = l;
    }

    public void getAndIncrement () { //普通方法调用
        long temp = get();           //调用已同步的读方法
        temp += 1L;                  //普通写操作
        set(temp);                   //调用已同步的写方法
    }
    public synchronized long get() {
        //对单个的普通变量的读用同一个监视器同步
        return vl;
    }
}
