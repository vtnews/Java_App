package com.mufeng.reorder;

public class PossibleReordering {

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    /**
     * 未出现重排序
     */
    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            a = 1;
            x = b;
        });
        Thread two = new Thread(() -> {
            b = 1;
            y = a;
        });
        one.start();two.start();
        one.run();  two.run();
        System.out.println("{" + x + "," + y + "}");
    }
}
