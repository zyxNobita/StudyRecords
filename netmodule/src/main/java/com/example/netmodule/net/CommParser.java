package com.example.netmodule.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.netmodule.net.helper.Helper;
import com.example.netmodule.net.net.Net;
import com.example.netmodule.net.net.Result;

/**
 * Created by Administrator on 2016/3/17.
 */
public class CommParser<T> implements Net.Parser<T> {

    private String mKey;

    public CommParser(String key) {
        mKey = key;
    }

    @Override
    public Result<T> parse(String response) {
        Result<T> result = new Result<T>();
        try {
            JSONObject baseObject = JSON.parseObject(response);
            if (baseObject.getInteger("error_code") != 0) {
                result.setResult(baseObject.getString("result"));
            } else {
                Class<T> klass = Helper.generateType(getClass());
                if (klass == null)
                    throw new Exception();
                T t = baseObject.getObject(mKey, klass);
                result.setResultT(t);
                result.setError_code(baseObject.getInteger("error_code"));
                result.setReason(baseObject.getString("reason"));
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setResult(Net.ERR_PARSE_MSG);
        }

        result.setError_code(Result.ERROR);
        return result;
    }
}