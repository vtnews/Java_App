package com.mufeng.SteamAPI;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by mufeng on 2017/12/22.
 */
public class PassingMethod {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        //1.传统做法
        List<Apple> greenApples = filterGreenApples(inventory);
        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> heavyApples = filterHeavyApples(inventory);
        // [Apple{color='green', weight=155}]

        //2.方法引用
        greenApples = filterApples(inventory, PassingMethod::isGreenApple);
        heavyApples = filterApples(inventory, PassingMethod::isHeavyApple);

        //3.Lambda——匿名函数
        greenApples = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        heavyApples = filterApples(inventory, (Apple a) -> a.getWeight() > 150);

        // []
        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 ||
                "brown".equals(a.getColor()));
        System.out.println(weirdApples);
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}

