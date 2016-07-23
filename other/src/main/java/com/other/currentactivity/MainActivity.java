package com.other.currentactivity;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.other.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
