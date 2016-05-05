package com.other.generic;

/**
 * Created by Administrator on 2016/4/29.
 */
public class Data<T> {
    private T data;

    public Data() {
    }

    public Data(T data) {
        setData(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
