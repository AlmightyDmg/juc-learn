package cn.com.dmg.juc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * Arrays
 * Collections
 * Executors
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        //List list = new ArrayList();
        //List list = Arrays.asList("a","b");
        //固定数的线程池，一池五线程

       //ExecutorService threadPool =  Executors.newFixedThreadPool(5); //一个银行网点，5个受理业务的窗口
       //ExecutorService threadPool =  Executors.newSingleThreadExecutor(); //一个银行网点，1个受理业务的窗口
       ExecutorService threadPool =  Executors.newCachedThreadPool(); //一个银行网点，可扩展受理业务的窗口

        //10个顾客请求
        try {
            for (int i = 1; i <=10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
 
 
