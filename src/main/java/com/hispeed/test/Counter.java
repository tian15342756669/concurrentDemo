package com.hispeed.test;

/**
 * Created by dengtianguang on 2018/9/4.
 */
public class Counter {

    private long value = 0L;

    /**
     * 两个方法上，都加Synchronized 关键字，能够保证原子性和可见性，
     * 但是会导致频繁的上下文切换，严重影响性能
     * @return
     */

    public synchronized long getValue(){
        return value;
    }

    public synchronized long increment(){
        return ++value;
    }
}
