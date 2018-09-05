package com.hispeed.futrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by dengtianguang on 2018/9/5.
 */
public class CompletionServiceTest {

    public static class FutrueTask implements Callable<Integer>{
        private Integer num;

        public FutrueTask(Integer num){
            this.num = num;
        }

        @Override
        public Integer call() throws Exception {

            System.out.println(Thread.currentThread().getName());
            return num;
        }
    }

    /**
     * Callable方法接受线程返回结果
     */
    public static void testCallAble() throws ExecutionException, InterruptedException {

        System.out.println("============"+ "callable start:");

        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future<Integer>> result = new ArrayList<Future<Integer>>();

        int max = 10;
        for (int i = 0; i < max ; i++) {
            Future<Integer> submit = executorService.submit(new FutrueTask(i));
            result.add(submit);
        }

        for (Future<Integer> i:result) {
            System.out.println("返回结果："+ i.get());
        }

        System.out.println("============"+ "callable end:");
    }

    /**
     * CompletionService方式实现线程返回值接收
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void testCompletionService() throws InterruptedException, ExecutionException {

        System.out.println("main Thread begin:");
        ExecutorService executor = Executors.newCachedThreadPool();
        // 构建完成服务
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
        for (int i = 0;i<10;i++) {
            completionService.submit(new FutrueTask(i));
        }
        for (int i = 0;i<10;i++) {//一个一个等待返回结果
            System.out.println("返回结果："+ completionService.take().get());
        }
        System.out.println("main Thread end:");
    }
}
