package com.hispeed.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dengtianguang on 2018/9/4.
 */
public class NonblockingCounter {

    /**
     *
     *
     * public final int incrementAndGet(){
     *   //死循环，一直尝试，直到成功
         for(;;){
         int current = getValue();
         int next = value + 1;
             if(compareAndSet(current,next)){
             return next;
             }
            }
         }
     */

 /*   public final boolean compareAndSet(int expect, int update){
        return unsafe.compareAndSwapInt(this,valueOffset,expect,unsafe);
    }

    */

    private AtomicInteger value;

    public int getValue() {
        return value.get();
    }
    public int increment() {

        return value.incrementAndGet();
    }
}
