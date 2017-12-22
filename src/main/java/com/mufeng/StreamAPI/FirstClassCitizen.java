package com.mufeng.StreamAPI;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by mufeng on 2017/12/22.
 */
public class FirstClassCitizen {

    /**
     * 获取目录下隐藏文件
     */
    public void demo() {
        //1.传统做法
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        //2.方法引用::语法
        hiddenFiles = new File(".").listFiles(File::isHidden);
        //3.Lambda——匿名函数
        hiddenFiles = new File(".").listFiles(file -> file.isHidden());
    }

    public static void main(String[] args) {

    }
}
