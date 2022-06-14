package cn.com.dmg.twenty.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();

    //不用lock unlock是因为，在读写的时候都会锁上
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t开始写入数据" + value);
            //暂停一会儿线程
            try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace(); }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "\t写入成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){

        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t开始读取数据");
            //暂停一会儿线程
            try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace(); }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取成功" + result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }


    }
}

/**
 * 之前的情况是：读的时候锁，写的时候也锁
 * 但是，只有写的时候需要锁，读的时候不需要锁
 *
 * 多个线程同一时间读一个资源没有任何问题，所以为了满足并发量，读取资源时应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源的时候，，就不应该再有其他线程可以对资源进行读或者写操作
 * 小总结：
 *      读读能共存
 *      读-写不能共存
 *      写-写不能共存
 */
public class ReadWriteLockDemo{

    public static void main(String[] args) {

        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }


    }

}
