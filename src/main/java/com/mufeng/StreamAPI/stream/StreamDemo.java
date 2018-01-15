package com.mufeng.StreamAPI.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by mufeng on 2017/12/23.
 */
public class StreamDemo {
    public static void main(String[] args) {
        String[] arrayOfWords = {"Goodbye", "World1", "World2", "World3", "World4", "World5"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        //1,不正确的去重方式
        List<String[]> collect1 = Arrays.stream(arrayOfWords)
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());

        List<Stream<String>> collect = Arrays.stream(arrayOfWords)
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());

        //2,正确的去重字母方式
        List<String> uniqueCharacters =
                Arrays.stream(arrayOfWords)
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(toList());

        Arrays.stream(arrayOfWords).anyMatch(str -> str.endsWith("o"));
        Arrays.stream(arrayOfWords).findAny();

    }
}
