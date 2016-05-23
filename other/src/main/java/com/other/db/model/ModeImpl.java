package com.other.db.model;

import com.other.db.entity.Info;

/**
 * Created by Administrator on 2016/5/23.
 */
public class ModeImpl implements Model {


    @Override
    public void initDB() {

    }

    @Override
    public boolean addRow(Info info) {
        return false;
    }

    @Override
    public Object queryRow(String num) {
        return null;
    }

    @Override
    public boolean delete(String num) {
        return false;
    }

    @Override
    public boolean update(String num, String age) {
        return false;
    }
}
