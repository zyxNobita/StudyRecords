### [adb 命令行的使用]()

####[官方文档讲解](https://developer.android.com/studio/command-line/adb.html)

查看adb版本信息：
```javascript
adb version
```

关闭adb服务：
```javascript
adb kill-server
```

开启adb服务
```javascript
adb start-server
```

查看连接的设备
```javascript
adb devices
```

安装test.apk(路径为盘根目录)
```javascript
adb install E:\test.apk
```

卸载test.apk(包名：com.test)
```javascript
adb uninstall com.test
```

test.apk设备中已经存在,重新安装(其实就是先卸载，再安装)
```javascript
adb install -r E:\test.apk
```

test.apk在设备已经存在，需要保留缓存数据后卸载应用程序
```javascript
adb uninstall -k com.test
```


连接多个设备时，只选择某一个设备安装test.apk(其中一个设备名字：B6G9GLGI8J0W8)
```javascript
adb -s B6G9GLGI8J0W8 install E:\test.apk
```

列出目标设备上的activity栈(back stack)和任务(task)的信息, 还有其他组件的一些信息和一些关于进程的信息
```javascript
adb shell dumpsys activity
```

列出一些系统信息和所有应用的信息。这个命令的输出很庞大，在三星nexus上的输出有12000多行。这些信息
都非常详细，包括Features，Activity Resolver Table等。
```javascript
adb shell dumpsys packages
```

列出设备上的所有权限
```javascript
adb shell pm list permissions
```

列出设备上所有应用的包名
```javascript
adb shell pm list packages
```

列出设备上所有的Features(不知道是用来干什么的)
```javascript
adb shell pm list features
```
启动指定应用的某个Activity(包名：com.example)
```javascript
adb shell am -start -n com.example/.MainActivity
```
在这里有可能会遇到一个java.lang.SecurityException: Permission Denial: starting Intent的异常，这是由于要启动的这个Activity没有添加android:exported="true"这个属性，导致不能通过第三方来启动。

截屏
```javascript
adb shell screencap -p | perl -pe 's/\x0D\x0A/\x0A/g' > screen.png
```

点亮/关闭 屏幕（相当于点击电源开关）
```javascript
adb shell input keyevent 26
```
解锁屏幕（有密码无效，只能进入输入密码的界面）
```javascript
adb shell input keyevent 82
```

清除APP数据(package:com.example)
```javascript
adb shell pm clear com.example
```

在命令行窗口打印log(停止：Ctrl +　Z)
```javascript
adb logcat
```

使用命令行打印log，并使用Tag进行过滤(TAG为EXAMPLE)
```javascript
adb logcat  -s EXAMPLE
```

命令行窗口中使用LOG级别来过滤log
```javascript
adb logcat "*:W"
```
#####V - Verbose (最低)
#####D - Debug
#####I - Info
#####W - Warning
#####E - Error
#####F - Fatal
#####S - Silent (最高, 使用这个级别将什么也不会打印)



