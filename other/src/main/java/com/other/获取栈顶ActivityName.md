#### [应用中获取栈中最顶层的Activity Name](https://github.com/ZQiang94/StudyRecords/blob/master/other/src/main/java/com/other/%E8%8E%B7%E5%8F%96%E6%A0%88%E9%A1%B6ActivityName.md)

Android 5.0版本前后获取topActivity有所不同，在代码中可以根据不同版本获取使用不同的方式获取topActivity。
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

参考：
##### [应用锁之获取栈顶Activity](http://www.jianshu.com/p/eb531b2d1d8e)