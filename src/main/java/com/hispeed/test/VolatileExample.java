package com.hispeed.test;

/**
 * Created by dengtianguang on 2018/9/4.
 */
public class VolatileExample {


    /**
     *
     * 假设线程A执行writer()方法之后，线程B执行reader()方法。
     * 根据happens before规则，这个过程建立的happens before 关系可以分为两类：
       根据程序次序规则，1 happens before 2; 3 happens before 4。
       根据volatile规则，2 happens before 3。
       根据happens before 的传递性规则，1 happens before 4。
       上述happens before 关系的图形化表现形式如下：
     */

    int a = 0;
    volatile boolean flag = false;

    public void writer() {
        a = 1;                   //1
        flag = true;               //2
    }


    public void reader() {
        if (flag) {                //3
            int i =  a;           //4

            //do something....
        }
    }
}
