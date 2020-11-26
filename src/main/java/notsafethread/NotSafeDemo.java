package notsafethread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

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
       List<String> list = new ArrayList();//new CopyOnWriteArrayList<>(); //Collections.synchronizedList(new ArrayList<>());//new Vector<>();
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
