package cn.com.dmg;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        //同步，异步，异步回调

        //异步
//        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
//            System.out.println(Thread.currentThread().getName()+"\t completableFuture1");
//        });
//        completableFuture1.get();

        //异步回调
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t completableFuture2");
            int i = 10/0;
            return 1024;
        });

        completableFuture2.whenComplete((t,u)->{
            System.out.println("-------t="+t); //t为返回值  当发生异常的时候 t返回null
            System.out.println("-------u="+u); //u为Throwable 当发生异常的时候 返回异常类
        }).exceptionally(f->{//发生异常的时候执行
            System.out.println("-----exception:"+f.getMessage());
            return 444;//返回给CompletableFuture
        }).get();

    }
}
 
 
