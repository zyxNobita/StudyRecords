package com.other.builder;

/**
 * Created by ZQiang94 on 2016/7/13.
 */

public class Student {

    //必须的参数
    private int age;
    private Sex sex;
    private String name;

    //非必须的参数，有默认值
    private String hobby;
    private boolean isSmokes;

    public static class Buidler{
        //必须的参数
        private int age;
        private Sex sex;
        private String name;

        //非必须的参数，有默认值
        private String hobby = "无";
        private boolean isSmokes = false;

        public Buidler(String name, int age, Sex sex){
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public Buidler hobby(String hobby){
            this.hobby = hobby;
            return this;
        }

        public Buidler isSmokes(boolean isSmokes){
            this.isSmokes = isSmokes;
            return this;
        }

        public Student builder(){
            return new Student(this);
        }

    }

    public Student(Buidler buidler){
        name = buidler.name;
        sex = buidler.sex;
        age = buidler.age;
        hobby = buidler.hobby;
        isSmokes = buidler.isSmokes;
    }

}
