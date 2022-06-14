package cn.com.dmg.twenty.juc.providercustomer;

/**
 * 资源类
 */
class AirContitioner{
    private int number = 0;

    //+1
    public synchronized void increment() throws InterruptedException{
        //1.判断
        while (number!=0){
            this.wait();
        }
        //2.干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" +number);
        //3.通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException{
        //1.判断
        while (number==0){
            this.wait();
        }
        //2.干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number );
        //3.通知
        this.notifyAll();
    }

}
