package cn.com.dmg.twenty.juc.ticket;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int number = 30;

    /**
     * 这里不再方法上添加 synchronized  -->public synchronized void saleTicket
     *
     * 改为使用JUC的Lock类
     *
     * 在方法上加synchronized的时候，会锁住方法内的所有代码，而需要锁的可能只有某几行代码，这样性能会降低
     */

    private Lock lock = new ReentrantLock();

    public void saleTicket(){
        lock.lock();
        try{
            if(number >= 1){
                System.out.println(Thread.currentThread().getName() + "：卖出的第" + number-- +"张票，还剩" + number + "张票。");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}

/**
 * 卖票系统
 *     三个售货员 卖出30张票
 *
 * 多线程企业级编程 套路+模板
 *  1.在高内聚，低耦合的前提下  线程          操作          资源类
 */
public class SaleTickets {
    public static void main(String[] args) {

        final Ticket tickets = new Ticket();

        //匿名内部类写法
//        new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    tickets.saleTicket();
//                }
//
//            }
//        },"A").start();//start之后不是立刻执行，而是线程进入了【就绪】状态，等待cpu和系统的调度
//
//        new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    tickets.saleTicket();
//                }
//            }
//        },"B").start();
//
//        new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    tickets.saleTicket();
//                }
//            }
//        },"C").start();

        //Lambda
        new Thread(() -> {for (int i = 0; i < 40; i++) tickets.saleTicket();},"A").start();
        new Thread(() -> {for (int i = 0; i < 40; i++) tickets.saleTicket();},"B").start();
        new Thread(() -> {for (int i = 0; i < 40; i++) tickets.saleTicket();},"C").start();


    }
}
