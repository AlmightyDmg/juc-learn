package cn.com.dmg.juc.notsafethread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 题目：举例说明线程不安全
 *
 * 解决故障的方法：
 *  1.故障现象
 *      java.util.ConcurrentModificationException
 *  2.导致原因
 *      add方法没有synchronized
 *
 *  3.解决方案
 *      ！！！不能答加锁
 *      3.1 改为Vector  add方法有同步锁 性能低
 *      3.2 Collections.synchronizedList(new ArrayList<>())  数据量不是很大
 *      3.3 new CopyOnWriteArrayList<>();
 *  4.优化建议（同样的错误不出现第二次）
 */
public class NotSafeDemo {
    public static void main(String[] args) {
        listNoSafe();
    }


    public static void listNoSafe(){
        List<String> list = new ArrayList();//new CopyOnWriteArrayList<>(); //Collections.synchronizedList(new ArrayList<>());//new Vector<>();
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    public static void setNoSafe(){
        //set的底层实现是hashmap
        Set<String> set = new CopyOnWriteArraySet<>(); //Collections.synchronizedSet(new HashSet<String>());//new HashSet();
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    public static void mapNoSafe(){
        /**
         * hashmap的由数组（Node类型）和链表+红黑树组成
         *  1.数组的初始大小为16，负载因子为0.75
         *  2.当数组的大小为16*0.75=12的时候，就会进行扩容，变为原来的一倍，初始：2^4变为2^5
         *  3.优化建议：
         *      1）将数组的初始大小改大，减少扩容造成的时间资源的浪费
         */
        Map<String,String> map =  new ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());// new HashMap<>();
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }


}
