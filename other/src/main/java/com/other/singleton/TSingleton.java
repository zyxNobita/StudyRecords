package com.other.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/5/5.
 * 
 * 单例模式可以使用多线程并发进行测试
 */
public class TSingleton {
    public static void main(String[] strings) {
        /**
         * CountDownLatch latch为闭锁， 所有线程中都用latch.await();等待锁释放，待所有线程初始化完成使用latch.countDown();释放锁，
         * 从而达到线程并发执行Singleton.getInstance()的效果。
         */
        final CountDownLatch latch = new CountDownLatch(1);
        int threadCount = 1000;

        for (int i = 0; i < threadCount; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        // all thread to wait
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // test get instance
                    System.out.println(Singleton.getIntance().hashCode());
                }
            }.start();
        }

        // release lock, let all thread excute Singleton.getInstance() at the same time
        latch.countDown();
    }
}
