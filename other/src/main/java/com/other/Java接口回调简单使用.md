#### [Java中回调接口的简单使用]()


Coding过程中可能会类似于这种的情景，删除ListView指定位置的item，这时候有多种实现方式，可以在适配器中，也可以使用回调的方式，将点击的item的索引回传到ui上，例如Fragment
或者Activity。然后再ui中得到相应item的索引来调用适配器（Adapter）的notifyDataSetChanged方法刷新listView。
要使用回调来实现类似的功能，大体有以下几个部分。具体根据自己实际情况而定。
这里以上面例子中描述的场景使用接口回调的方式来实现。

1.创建回调接口
```javascript
public interface OnButtonListener {
    void CallBack(int position);
}
```
2.“接口绑定”
```javascript
lvAdapter.setOnButtonListener(this);
```
实际就是将实现接口的实例以参数的形式传到要调用的类中，为此也可以这样写：
```javascript
lvAdapter.setOnButtonListener(new OnButtonListener() {
            @Override
            public void CallBack(int position) {
                list.remove(position);
                lvAdapter.notifyDataSetChanged();
                mTextView.setText("当前item个数为：" + list.size() + "");
            }
        });
```
3.实现接口，就是实现前面创建接口的方法，把本应该在另一个类中发生的动作拿到这个方法中实现。
下面这种写法是在接口绑定方法中传递的实现回调接口的类的实例，上面是直接new了一个实例。
故，这两种写法都对。
```javascript
@Override
public void CallBack(int position) {
    list.remove(position);
    lvAdapter.notifyDataSetChanged();
}
```
4.在调用回调接口的类中要创建该回调接口的实例，在绑定接口的方法中初始化。
```javascript
private OnButtonListener onButtonListener;

public void setOnButtonListener(OnButtonListener onButtonListener){
    this.onButtonListener = onButtonListener;
}
```

5.有了具体的接口变量，就可以直接调用这个接口变量中的方法，来将要实现的方法回调到实现接口的地方来实现。
```javascript
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onButtonListener.CallBack(position);
		}
    });
```
上面就是根据例子中的描述，使用java回调来实现的将item View的btn的点击事件回传到ui中来实现。
具体场景可以延伸到其他地方，总之，使用Java回调的地方还是比较多的。