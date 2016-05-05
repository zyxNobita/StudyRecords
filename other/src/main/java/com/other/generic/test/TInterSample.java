package com.other.generic.test;

import com.other.generic.AnimalGeneator;

/**
 * Created by Administrator on 2016/5/3.
 */
public class TInterSample {
    /**
     * 泛型方法 一个基本的原则是：无论何时，只要你能做到，你就应该尽量使用泛型方法。
     * 也就是说， 如果使用泛型方法可以取代将整个类泛化，那么应该有限采用泛型方法。
     */
    public static <T> void out(T t) {
        System.out.print(t + "\n");
    }

    public static void main(String[] strings) {
        //创建实现泛型接口的对象
        AnimalGeneator geneator = new AnimalGeneator();
        System.out.print(geneator.next() + "\n");
        System.out.print(geneator.next());
        out("this is string");
        out(10);
        out(0.1);
        TClassSample tClassSample = new TClassSample();
        out(tClassSample);
    }

}
