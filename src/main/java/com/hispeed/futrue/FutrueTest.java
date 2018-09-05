package com.hispeed.futrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dengtg on 2018-9-5.
 */
public class FutrueTest {

    public static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println("ah");
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        int last = 100;
        for (int i = 0; i < last ; i++) {
            executorService.submit(new Task());
        }
    }
}
