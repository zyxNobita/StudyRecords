package com.other.generic.test;

import com.other.generic.Container;

/**
 * Created by Administrator on 2016/5/3.
 */
public class TClassSample {
    public static void main(String[] strings) {
        Container<String, String> c1 = new Container<>("name","test");
        Container<String, Integer> c2 = new Container<>("age",24);
        Container<Double, Double> c3 = new Container<>(1.1, 2.2);
        System.out.println(c1.getKey() + " : " + c1.getValue());
        System.out.println(c2.getKey() + " : " + c2.getValue());
        System.out.println(c3.getKey() + " : " + c3.getValue());


    }
}
