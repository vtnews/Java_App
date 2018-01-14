package com.mufeng.finaldemo;

public class FinalReferenceEscapeExample {
    final int i;
    static FinalReferenceEscapeExample obj;

    public FinalReferenceEscapeExample () {
        i = 1; // 1写final域
        obj = this; // 2 this引用在此"逸出"
        /*
         * 首先，1和2无依赖关系，可能会重排序。
         * 2 h-b 3
         * 3 h-b 4
         *
         * 在初始化开辟空间时，2先执行，则obj这个普通变量已经被赋予了this值，确定指向的地址空间。而1的final还未初始化
         * 则变成：
         * ThreadB:    -> 3 -> 4 -
         * ThreadA: 2 -           -> 1
         * 则4中读取的，是还未执行1前的final变量i值。
         */
    }

    public static void writer() {
        new FinalReferenceEscapeExample ();
//        obj = new FinalReferenceEscapeExample (); //应该将逸出的去掉，在这赋值
    }

    public static void reader() {
        if (obj != null) { // 3
            int temp = obj.i; // 4
        }
    }
}