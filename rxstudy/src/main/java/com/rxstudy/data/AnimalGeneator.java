package com.rxstudy.data;

import java.util.Random;

/**
 * Created by Administrator on 2016/5/3.
 */


//实现泛型接口方法
public class AnimalGeneator implements Generator<String> {

    private String[] animals = new String[] { "dog", "pig", "cat", "null" };

    @Override
    public String next() {
        Random random = new Random();
        return animals[random.nextInt(4)];
    }
}
