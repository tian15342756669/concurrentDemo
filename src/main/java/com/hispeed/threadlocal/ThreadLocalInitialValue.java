package com.hispeed.threadlocal;

/**
 * Created by dengtg on 2018-9-5.
 */
public class ThreadLocalInitialValue {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };

    ThreadLocal<String> strThreadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue(){
            return Thread.currentThread().getName();
        }
    };

    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
        strThreadLocal.set(Thread.currentThread().getName());
    }

    public long getLong(){
       return longThreadLocal.get();
    }

    public String getStr(){
       return strThreadLocal.get();
    }


    /**
     * 总结一下：

     　　1）实际的通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中的；

     　　2）为何threadLocals的类型ThreadLocalMap的键值为ThreadLocal对象，因为每个线程中可有多个threadLocal变量，就像上面代码中的longLocal和stringLocal；

     　　3）在进行get之前，必须先set，否则会报空指针异常；

     　　     如果想在get之前不需要调用set就能正常访问的话，必须重写initialValue()方法。

     　　　   因为在上面的代码分析过程中，我们发现如果没有先set的话，即在map中查找不到对应的存储，
             则会通过调用setInitialValue方法返回i，而在setInitialValue方法中，有一个语句是T value = initialValue()，
             而默认情况下，initialValue方法返回的是null。
     * @param args
     */

    public static void main(String[] args) {

        final ThreadLocalInitialValue test = new ThreadLocalInitialValue();

        /**
         * todo 如果这里不set,下面的get 会报错
         */
        test.set();

        System.out.println("========== "+test.getLong());
        System.out.println("========== "+test.getStr());

        Thread thread = new Thread(){
            @Override
            public void run(){
                test.set();

                System.out.println("+++++++++++++ "+test.getLong());
                System.out.println("+++++++++++++ "+test.getStr());
            };
        };

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(test.getLong());
        System.out.println(test.getStr());
    }
}
