package com.hispeed.futrue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dengtg on 2018-9-5.
 */
public class FutrueCallableTest {

    public static class Task implements Callable<String>{

        @Override
        public String call() throws Exception {

            System.out.println("ah");
            return "Hello World!!!";
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        int max = 10;

        for (int i = 0; i < max ; i++) {
            executorService.submit(new Task());
        }
    }
}
