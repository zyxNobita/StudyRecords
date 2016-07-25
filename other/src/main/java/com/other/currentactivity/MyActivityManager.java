package com.other.currentactivity;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created by ZQiang94 on 2016/7/23.
 */
public class MyActivityManager {

    private static MyActivityManager sInstance = new MyActivityManager();
    /**
     * 那么为什么要使用弱引用持有Activity实例呢？
     * 其实最主要的目的就是避免内存泄露，因为使用默认的强引用会导致Activity实例无法释放，导致内存泄露的出现
     */
    private WeakReference<Activity> sCurrentActivityWeakRef;

    private MyActivityManager() {
    }

    public static MyActivityManager getInstance() {
        return sInstance;
    }

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (currentActivity != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
    }

}
