package cn.com.dmg.juc;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("*******come in here");
        return 1024;
    }
}

/**
 * 多线程中
 *  第三种获取多线程的方法
 *
 *  1.get方法一般请放在最后一行 调用get的时候就要等拿到结果
 *  2.当两个线程去调用同一个future的时候，方法只会被调用一次
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyThread myThread = new MyThread();

        FutureTask futureTask = new FutureTask(myThread);


        //只会调用一次
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        System.out.println(Thread.currentThread()
        .getName() + "计算完成");

        System.out.println(futureTask.get());

    }
}
