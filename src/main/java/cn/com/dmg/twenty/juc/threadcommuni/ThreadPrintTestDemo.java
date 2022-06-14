package cn.com.dmg.twenty.juc.threadcommuni;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class MyPrint{


    private Lock lock = new ReentrantLock();
    private Condition conditionNumber = lock.newCondition();
    private Condition conditionLetter = lock.newCondition();

    private int num = 1;

    public void printNumber(){
        lock.lock();
        try {
            for (int i = 1; i < 53; i++) {
                while (num%3==0){
                    conditionNumber.await();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + i);

                num++;

                conditionLetter.signal();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printLetter(){
        lock.lock();
        try {
            for (char i = 'A'; i <= 'Z'; i++) {
                while (num%3!=0){
                    conditionLetter.await();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + i);
                num++;
                conditionNumber.signal();
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }





}

/**
 * 两个线程，一个线程打印1-52，另一个打印字母A-Z打印顺序为12A34B...5152Z,
 * 要求用线程间通信
 */
public class ThreadPrintTestDemo {

    public static void main(String[] args) {


        MyPrint myPrint = new MyPrint();

        new Thread(()->{
            myPrint.printNumber();
        },"A").start();

        new Thread(()->{
            myPrint.printLetter();
        },"B").start();

        new Thread(()->{
            myPrint.printNumber();
        },"A1").start();

        new Thread(()->{
            myPrint.printLetter();
        },"B1").start();

    }

}
