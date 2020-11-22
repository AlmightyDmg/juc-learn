package cn.com.dmg.juc;

@FunctionalInterface
interface Foo{
    //public void sayHello();
    public int add(int a ,int b);

    //java8之后接口中可以有实现方法 修饰符为 default 可以有多个
    default int div(int a ,int b){
        return a/b;
    }

    default int div1(int a ,int b){
        return a/b;
    }

    //java8之后，接口中可以有静态方法的实现方法，可以有多个
    public static int mv(int a,int b){
        return a*b;
    }

    public static int mv1(int a,int b){
        return a*b;
    }
}

public class LambdaDemo {
    public static void main(String[] args) {
        /**
         * 接口中有且只有一个方法
         *
         * 1.口诀：拷贝小括号，写死右箭头，落地大括号
         *
         * 2.函数式编程注解：@FunctionalInterface  当只有一个方法的时候，会默认添加这个注解
         *
         * 3.接口里面能否有方法的实现（java8之前不能，java8后可以有） default方法，可以有多个default方法
         *
         * 4.静态方法实现 可以有多个
         *
         */

    //        Foo f = () -> { System.out.println("hello lambdaDemo"); };
    //        f.sayHello();
          Foo f = (a ,b) -> {
              System.out.println("带参数的lambda表达式写法");
              return a + b;
          };
          System.out.println(f.add(1,2));
          System.out.println(f.div(10,5));
          Foo.mv(2,5);

    }
}

