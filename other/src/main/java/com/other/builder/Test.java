package com.other.builder;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by ZQiang94 on 2016/7/13.
 */

public class Test extends Activity{

    Student mStudent;
    Teacher mTeacher;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mStudent = new Student.Buidler("ZQiang94",20,Sex.MAN).hobby("coding").builder();
        mTeacher = new TeacherBuilder().setAge(20).setName("ZQiang94").createTeacher();
    }
}
