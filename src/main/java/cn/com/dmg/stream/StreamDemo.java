package cn.com.dmg.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private Integer id;
    private String userName;
    private int age;

}

/**
 * 题目：
 *  请按照给出数据，找出同时满足以下条件的用户，也即以下条件全部满足
 *      偶数id 且 年龄大于24 且 用户名转为大写用户名且字母倒排序
 *      且只输出一个用户名字
 */
public class StreamDemo {
    public static void main(String[] args) {

        User u1 = new User(11,"a",23);
        User u2 = new User(12,"b",24);
        User u3 = new User(13,"c",22);
        User u4 = new User(14,"d",28);
        User u5 = new User(16,"e",26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.forEach(user->{
            System.out.println(user.getAge());
        });

        list.forEach(user->System.out.println(user.getAge()));
        list.forEach(System.out::println);

        list.stream().filter(p -> {
            return p.getId() % 2 == 0;
        }).filter(p -> {
            return p.getAge() > 24;
        }).map(f -> {
            return f.getUserName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);


    }

    public static void functionInterface(){
        //R apply(T t);函数型接口，一个参数，一个返回值
        Function<String,Integer> function = t ->{return t.length();};
        System.out.println(function.apply("abcd"));

        //boolean test(T t);断定型接口，一个参数，返回boolean
        Predicate<String> predicate = t->{return t.startsWith("a");};
        System.out.println(predicate.test("a"));

// void accept(T t);消费型接口，一个参数，没有返回值
        Consumer<String> consumer = t->{
            System.out.println(t);
        };
        consumer.accept("javaXXXX");

//T get(); 供给型接口，无参数，有返回值
        Supplier<String> supplier =()->{return UUID.randomUUID().toString();};
        System.out.println(supplier.get());


    }
}
