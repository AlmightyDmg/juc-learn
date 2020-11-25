package cn.com.dmg.juc.providercustomer;

/**
 * 这个类有两个生产者，两个消费者，会出现多加，多减的问题
 */
public class ThreadWaitNotifyDemo4Threads {

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
        },"A0").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airContitioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A1").start();

        //消费者
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airContitioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B0").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airContitioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B1").start();
    }

}
