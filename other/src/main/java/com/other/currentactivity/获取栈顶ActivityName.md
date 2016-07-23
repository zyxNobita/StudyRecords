#### [应用中获取栈中最顶层的Activity Name](https://github.com/ZQiang94/StudyRecords/blob/master/other/src/main/java/com/other/%E8%8E%B7%E5%8F%96%E6%A0%88%E9%A1%B6ActivityName.md)

1.Android 5.0版本前后获取topActivity有所不同，在代码中可以根据不同版本获取使用不同的方式获取topActivity。
```javascript
        String packname = ""; /* Android5.0之后获取程序锁的方式是不一样的*/
        String activityname = "";
        if (Build.VERSION.SDK_INT > 20) {
            // 5.0及其以后的版本
            List<ActivityManager.RunningAppProcessInfo> tasks = ((ActivityManager) this.getSystemService(ACTIVITY_SERVICE)).
                    getRunningAppProcesses();
            if (null != tasks && tasks.size() > 0) {
                packname = tasks.get(0).processName;
            }
        } else {
            // 5.0之前
            // 获取正在运行的任务栈(一个应用程序占用一个任务栈) 最近使用的任务栈会在最前面
            // 1表示给集合设置的最大容量 List<RunningTaskInfo> infos = am.getRunningTasks(1);
            // 获取最近运行的任务栈中的栈顶Activity(即用户当前操作的activity)的包名
            List<ActivityManager.RunningTaskInfo> infos = ((ActivityManager) this.getSystemService(ACTIVITY_SERVICE)).getRunningTasks(1);

            packname = infos.get(0).topActivity.getPackageName();
            activityname = infos.get(0).topActivity.getClassName();
        }

        Log.e("packname:", packname.toString());
        Log.e("activityname:", activityname.toString());
    }
```
除此之外还要加上GET_TASKS的权限
```javascript
<uses-permission android:name = "android.permission.GET_TASKS"/>
```

2.使用Application的registerActivityLifecycleCallbacks方法，用来监听所有Activity的生命周期回调，
比如onActivityCreated,onActivityResumed等。
但是该方法只支持Android自 API 14，不过现在大多数设备都是在API 14或以上。
具体实现如下：
在Application的onCreate的方法中：
```javascript
@Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
```
其中MyActivityManager类如下：
```javascript
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

    public MyActivityManager getInstance() {
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
````

参考：
##### [应用锁之获取栈顶Activity](http://www.jianshu.com/p/eb531b2d1d8e)
##### [关于获取当前Activity的一些思考](http://droidyue.com/blog/2016/02/21/thinking-of-getting-the-current-activity-in-android/)