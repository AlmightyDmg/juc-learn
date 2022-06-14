package cn.com.dmg.twenty.juc.utildemo;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位  //等于1的时候相当于一个锁，一次只能执行一个线程

        for (int i = 1; i <=6; i++) //模拟6部汽车
        {
            new Thread(() -> {
                try
                {
                    semaphore.acquire();//占用一个资源
                    System.out.println(Thread.currentThread().getName()+"\t 抢到了车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+"\t------- 离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放资源
                }
            }, String.valueOf(i)).start();
        }

    }

}
