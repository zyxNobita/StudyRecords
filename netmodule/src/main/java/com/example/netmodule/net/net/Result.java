package com.example.netmodule.net.net;

/**
 * Created by qibin on 2015/11/29.
 */
public class Result<T> {
    public static final int ERROR = 0;
    public static final int SUCCESS = 1;

    private int error_code;// 返回码
    private String reason;// 返回说明
    private String result;// 返回结果集
    private T resultT;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getResultT() {
        return resultT;
    }

    public void setResultT(T resultT) {
        this.resultT = resultT;
    }
}
