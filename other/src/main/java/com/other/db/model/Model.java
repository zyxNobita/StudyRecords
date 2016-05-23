package com.other.db.model;

import com.other.db.entity.Info;

/**
 * Created by Administrator on 2016/5/23.
 */
public interface Model {

    public void initDB();

    public boolean addRow(Info info);

    public Object queryRow(String num);

    public boolean delete(String num);

    public boolean update(String num,String age);

}
