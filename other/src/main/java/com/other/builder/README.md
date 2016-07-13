##### [Builder 链式编程替代构造器](http://coolview.github.io/2016/04/28/Effective-Java-%E8%AF%BB%E4%B9%A6%E7%AC%94%E8%AE%B002-%E9%81%87%E5%88%B0%E5%A4%9A%E4%B8%AA%E6%9E%84%E9%80%A0%E5%99%A8%E5%8F%82%E6%95%B0%E6%97%B6%E8%A6%81%E8%80%83%E8%99%91%E7%94%A8%E6%9E%84%E5%BB%BA%E5%99%A8/)

静态工厂和构造器有个共同的局限性，就是它们都不能很好地拓展到大量的可选参数。
对于这样有大量可选参数的类，如果使用构造函数的方式来构建这样的类（重叠构造器），就显得笨重。

还有一种方式应对有大量可选参数的类的方式，就是使用JavaBean的set方法来对每一个参数进行赋值，但是这种方式
有一个比较大的缺陷就是因为构造过程被分到了几个调用中，在构造的过程中JavaBean可能会处于不一致的状态。还有
一点需要程序员做的就是要确保这个类的线程安全。

针对这种具有大量可选参数的类，我们可以使用builder模式。
Builder模式，既能保证像重叠构造器模式那样的安全性，也能保证像JavaBeans模式那么好的可读性。
不直接生成想要的对象，而是让客户端利用所有必要的参数调用构造器（或者静态工厂），得到一个Builder对象。
然后客户端在builder对象上调用类似于setter的方法，来设置每个相关的可选参数。最后，客户端调用无参的build方法来生成不可变的对象。
这个builder是他构建的类的静态成员类。

```javascript
public class Student {

    //必须的参数
    private int age;
    private Sex sex;
    private String name;

    //非必须的参数，有默认值
    private String hobby;
    private boolean isSmokes;

    public static class Buidler{
        //必须的参数
        private int age;
        private Sex sex;
        private String name;

        //非必须的参数，有默认值
        private String hobby = "无";
        private boolean isSmokes = false;

        public Buidler(String name, int age, Sex sex){
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public Buidler hobby(String hobby){
            this.hobby = hobby;
            return this;
        }

        public Buidler isSmokes(boolean isSmokes){
            this.isSmokes = isSmokes;
            return this;
        }

        public Student builder(){
            return new Student(this);
        }
    }

    public Student(Buidler buidler){
        name = buidler.name;
        sex = buidler.sex;
        age = buidler.age;
        hobby = buidler.hobby;
        isSmokes = buidler.isSmokes;
    }
}
```
编写代码的时候可能会有点繁琐，但是Android Studio中有提供Builder的方式：
创建类（成员变量与构造方法） -- >  在构造方法上右键  -- >  refactor  -- >  Replease Constructor with Builder...

<img src="http://img.blog.csdn.net/20150903224054423">
<img src="http://img.blog.csdn.net/20150903224237108">