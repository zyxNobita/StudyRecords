package com.rxstudy.data;

/**
 * Created by Administrator on 2016/5/3.
 */

//泛型接口
public interface Generator<T> {
    public T next();
}
