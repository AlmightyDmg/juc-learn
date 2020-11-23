package cn.com.dmg.juc.providercustomer;
/**
 * 题目：
 *      现在两个线程，可以操作初始值为零的变量
 *      实现一个线程对该变量+1，一个线程对该变量-1
 *      实现交替，来10轮，变量初始值为0
 *
 * 1.高内聚低耦合的前提下，线程操作资源类
 * 2.生产者消费者模式套路：判断/干活/通知
 * 3.多线程交互中，必须要防止多线程的虚假唤醒，也即：判断只能用while，不能用if
 */

/**
 * 这个类只有一个生产者，一个消费者，不会出现问题
 */
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) {
        AirContitioner airContitioner = new AirContitioner();

        //生产者
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airContitioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        //消费者
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airContitioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

    }

}
