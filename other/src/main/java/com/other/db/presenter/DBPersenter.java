package com.other.db.presenter;

/**
 * Created by Administrator on 2016/5/23.
 */
public interface DBPersenter {

    public void initDB();

    public void addRow(String num);

    public void queryRow(String num);

    public void delete(String num);

    public void update(String age);

}
