package cn.com.dmg.twenty.juc.utildemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 到达一定数量的时候才开始执行
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        //不会阻塞主线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            try {
                Thread.sleep(10000);
                System.out.println(list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("集齐七颗龙珠，召唤神龙！");
        });

        for (int i = 0; i < 7; i++) {
            final int tempInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t收集到第" + tempInt + "颗龙珠");
                list.add(String.valueOf(tempInt));
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t 班长关门走人");
    }
}
