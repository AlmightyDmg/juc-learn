package cn.com.dmg.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
    private int number =1;//1:A/2:B/3:C
    private Lock lock = new ReentrantLock();
    //一个锁 配三把钥匙
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            while (number != 1){
                //不是1 就不该A干活 A的线程等待
                condition1.await();
            }

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }

            //通知 标志位变为2  通知2 唤醒
            number = 2;
            condition2.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while (number != 2){
                condition2.await();
            }

            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }

            number = 3;
            condition3.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while (number != 3){
                //不是1 就不该A干活 A的线程等待
                condition3.await();
            }

            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }

            //通知 标志位变为2  通知2 唤醒
            number = 1;
            condition1.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * 多线程之间按顺序调用：实现A->B->C  下订单-减库存-支付
 * 三个线程启动，要求如下
 *  A打印5次，B打印10次，C打印15次
 *  接着
 *  A打印5次，B打印10次，C打印15次
 *      重复10轮
 *
 *  体现新技术的厉害之处
 *
 *  1.高内聚低耦合的前提下，线程操作资源类
 *  2.生产者消费者模式套路：判断/干活/通知
 *  3.多线程交互中，必须要防止多线程的虚假唤醒，也即：判断只能用while，不能用if
 *  4.标志位
 *
 */
public class ThreadOrderAccess {
    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        },"C").start();


    }
}
