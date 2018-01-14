package com.mufeng.finaldemo;

public class FinalReferenceEscapeExample {
    final int i;
    static FinalReferenceEscapeExample obj;

    public FinalReferenceEscapeExample () {
        i = 1; // 1写final域
        obj = this; // 2 this引用在此"逸出"
    }

    public static void writer() {
        new FinalReferenceEscapeExample ();
//        obj = new FinalReferenceEscapeExample (); //应该将逸出的去掉，在这实例化
    }

    public static void reader() {
        if (obj != null) { // 3
            int temp = obj.i; // 4
        }
    }
}