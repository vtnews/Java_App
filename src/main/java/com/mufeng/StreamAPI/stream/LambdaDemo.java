package com.mufeng.StreamAPI.stream;

import com.mufeng.StreamAPI.PassingMethod.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class LambdaDemo {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();//<> 菱形运算符，jdk7中，可以用来推测类型。
        //1.传统排序
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        //2.lambda
        inventory.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));
        //3.
        inventory.sort(comparing(Apple::getWeight));

        //1.传统Runnable
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world");
            }
        });
        //2.lambda
        t = new Thread(() -> System.out.println("Hello world"));

//        Predicate<Apple> p = (Apple a) -> a.getWeight();

        //Predicate
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(Arrays.asList("1","2","3","4","5"), nonEmptyStringPredicate);

        //Consumer
        forEach(Arrays.asList(1,2,3,4,5),   (Integer i) -> System.out.println(i));

        //Function
        List<Integer> l = map(
                Arrays.asList("lambdas","in","action"),
                (String s) -> s.length()
        );

        //比较器复合
        //1，逆序
        inventory.sort(comparing(Apple::getWeight).reversed());
        //2，比较器链
        inventory.sort(
                comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));

        //谓语复合
        Predicate<Apple> redApple = (Apple a) -> "red".equals(a.getColor());
        Predicate<Apple> notRedApple = redApple.negate();//产生redApple的非
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);//and复合
        Predicate<Apple> redAndHeavyAppleOrGreen = redAndHeavyApple.or(a -> "green".equals(a.getColor()));//or
        //不一样的是，这个是从左到右的优先级。是顺序执行过去的。a.or(b).and(c)可以看作(a || b) && c。

        //函数复合

    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for(T s: list){
            if(p.test(s)){
                results.add(s);
            } }
        return results;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T i: list){
            c.accept(i);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.apply(s));
        }
        return result;
    }
}
