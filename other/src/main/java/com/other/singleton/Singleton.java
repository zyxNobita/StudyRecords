package com.other.singleton;

/**
 * Created by Administrator on 2016/5/5.
 */
public class Singleton {

    private static volatile Singleton intance = null;

    private Singleton() {

    }

    public static Singleton getIntance() {
        if (intance == null) {
            synchronized (Singleton.class) {
                if (intance == null) {
                    intance = new Singleton();
                }
            }
        }

        return intance;
    }

    /**
     * 1、需要注意的点 其中需要注意的点主要有三点
     * (1) 私有化构造函数
     * (2) 定义静态的Singleton instance对象和getInstance()方法
     * (3) getInstance()方法中需要使用同步锁synchronized (Singleton.class)防止多线程同时进入造成instance被多次实例化
     * 可以看到上面在synchronized (Singleton.class)外又添加了一层if，这是为了在instance已经实例化后下次进入不必执行synchronized
     * (Singleton.class)获取对象锁，从而提高性能。
     *
     * Ps: 也有实现使用的是private static Object obj = new Object();
     * 加上synchronized(obj)，实际没有必要多创建一个对象。synchronized(X.class)
     * is used to make sure that there is exactly one Thread in the block.
     */

    /**
     * synchronized
     * 同步块大家都比较熟悉，通过 synchronized 关键字来实现，所有加上synchronized 和 块语句，
     * 在多线程访问的时候，同一时刻只能有一个线程能够用
     *
     * volatile
     * 用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的最的值。
     */

}
