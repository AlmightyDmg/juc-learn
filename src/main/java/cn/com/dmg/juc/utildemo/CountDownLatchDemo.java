package cn.com.dmg.juc.utildemo;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        /**
         * 需求：
         *  要求所有人都走完之后才能“班长关门走人”
         *
         *  不使用JUC辅助类之前，
         */
//        for (int i = 0; i < 6; i++) {
//            new Thread(()->{
//                System.out.println(Thread.currentThread().getName() + "\t离开教室" );
//            },String.valueOf(i)).start();
//        }
//
//        System.out.println(Thread.currentThread().getName() + "\t 班长关门走人");

        //设置一共有6个线程
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t离开教室" );
                //一个线程执行完，就将countDownLatch中线程的数量-1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            //当countDownLatch计数器中的线程数变为0的时候，该await的线程才会被唤醒
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 班长关门走人");

    }


}
