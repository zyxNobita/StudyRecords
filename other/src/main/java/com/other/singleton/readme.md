#### [单例模式](http://www.trinea.cn/java/singleton/)

单例主要有两个作用
(1) 保持程序运行过程中该类始终只存在一个示例
(2) 对于new性能消耗较大的类，只实例化一次可以提高性能

这里主要介绍单例模式的一种写法、注意事项、作用、测试，以Java语言为例，下面代码是目前见过最好的写法：

```javascript
public class Singleton {

    private static volatile Singleton instance = null;

    // private constructor suppresses
    private Singleton(){
    }

    public static Singleton getInstance() {
        // if already inited, no need to get lock everytime
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
```
其中需要注意的点主要有三点
(1) 私有化构造函数
(2) 定义静态的Singleton instance对象和getInstance()方法
(3) getInstance()方法中需要使用同步锁synchronized (Singleton.class)防止多线程同时进入造成instance被多次实例化
可以看到上面在synchronized (Singleton.class)外又添加了一层if，这是为了在instance已经实例化后下次进入不必执行synchronized (Singleton.class)获取对象锁，从而提高性能。

Ps: 也有实现使用的是private static Object    obj      = new Object();加上synchronized(obj)，实际没有必要多创建一个对象。synchronized(X.class) is used to make sure that there is exactly one Thread in the block.
 