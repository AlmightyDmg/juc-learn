package cn.com.dmg.juc.tickets;


class Ticket{
    private int number = 30;

    public synchronized void saleTicket(){

        for (int i = 0; i < 40; i++) {
            if(number >= 1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "：卖出的第" + number-- +"张票，还剩" + number + "张票。");
            }
        }
    }

}

public class SaleTickets {
    public static void main(String[] args) {

        final Ticket tickets = new Ticket();

        new Thread(new Runnable() {
            public void run() {
                tickets.saleTicket();
            }
        },"A").start();

        new Thread(new Runnable() {
            public void run() {
                tickets.saleTicket();
            }
        },"B").start();

        new Thread(new Runnable() {
            public void run() {
                tickets.saleTicket();
            }
        },"C").start();

    }
}
