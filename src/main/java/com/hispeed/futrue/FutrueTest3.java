package com.hispeed.futrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by dengtg on 2018-9-5.
 */
public class FutrueTest3 {

    public static class Task implements Callable<String>{

        @Override
        public String call() throws Exception {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

            System.out.println("hahaha");

            return sdf.format(new Date());
        }
    }


    public static void main(String[] args) {

        List<Future<String>> futures = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int max = 100;
        for (int i = 0; i <max; i++) {
            futures.add(executorService.submit(new Task()));
        }

        for (Future<String> str : futures){
            try {
                System.out.println(" ================  " + str.get() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
