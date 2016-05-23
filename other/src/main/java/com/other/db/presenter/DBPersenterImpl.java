package com.other.db.presenter;

import com.other.db.entity.Info;
import com.other.db.model.ModeImpl;
import com.other.db.model.Model;
import com.other.db.ui.ViewInterface;

/**
 * Created by Administrator on 2016/5/23.
 */
public class DBPersenterImpl implements DBPersenter {
    private Model model;
    private ViewInterface mViewInterface;

    public DBPersenterImpl(ViewInterface mViewInterface) {
        model = new ModeImpl();
        mViewInterface = this.mViewInterface;
    }

    @Override
    public void initDB() {
        model.initDB();
    }

    @Override
    public void addRow(String num) {
        Info info = new Info();
        info.setAge("20");
        info.setName("张强");
        info.setNum(num);
        if (model.addRow(info)) {
            mViewInterface.showTxt("add success");
        } else {
            mViewInterface.showTxt("add faild");
        }
    }

    @Override
    public void queryRow(String num) {
        Info info = (Info) model.queryRow(num);
        if (info != null) {
            mViewInterface.showTxt(info.getName() + "\n" + info.getAge());
        } else {
            mViewInterface.showTxt("query is null or faild");
        }
    }

    @Override
    public void delete(String num) {
        if (model.delete(num)){
            mViewInterface.showTxt("delete success");
        }else{
            mViewInterface.showTxt("delete faild");
        }
    }

    @Override
    public void update(String age) {
        if (model.update("1",age)){
            mViewInterface.showTxt("update success");
        }else{
            mViewInterface.showTxt("update faild");
        }
    }
}
