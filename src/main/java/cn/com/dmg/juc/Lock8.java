package cn.com.dmg.juc;

import java.util.concurrent.TimeUnit;

class Phone{
    //问题5 static
    public static synchronized void sendEmail() throws Exception{
        //问题2 邮件方法暂停4秒钟（java8之后新的睡觉方法）
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendEmail");
    }
    //问题5 static
    public  synchronized void sendMsg() throws Exception{
        System.out.println("sendMsg");
    }

    public void hello() throws Exception{
        System.out.println("hello");
    }
}

/**
 * 多线程8锁
 * 1.标准访问，请问先打印邮件还是短信？ 邮件
 * 2.邮件方法暂停4秒钟，请问先打印邮件还是短信？ 邮件
 * 3.新增一个普通方法hello(),请问先打印邮件还是hello？ hello
 * 4.两部手机，先打印邮件还是短信？ 短信
 * 5.两个静态同步方法，同一部手机，请问先打印邮件还是短信？邮件
 * 6.两个静态同步方法，两部手机，请问先打印邮件还是短信？邮件
 * 7.一个静态同步方法，一个普通同步方法，同一部手机，请问先打印邮件还是短信？短信
 * 8.一个静态同步方法，一个普通同步方法，两部手机，请问先打印邮件还是短信？短信
 */
public class Lock8 {
    public static void main(String[] args) throws Exception{

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        /**
         * 正常情况下，主线程启动之后，不一定是先启动A，再启动B，全靠抢
         */
        new Thread(()->{
            try{
                phone.sendEmail();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"A").start();
        //为了保证先A后B 休眠200ms
        Thread.sleep(200);
        new Thread(()->{
            try{
                //phone.sendMsg();
                //phone.hello();//问题3 不发短信，改为hello
                phone2.sendMsg();//问题4
            }catch (Exception e){
                e.printStackTrace();
            }
        },"B").start();

    }
}
