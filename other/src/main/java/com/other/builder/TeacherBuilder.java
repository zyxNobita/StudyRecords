package com.other.builder;

public class TeacherBuilder {
    private String mName;
    private int mAge;

    public TeacherBuilder setName(String name) {
        mName = name;
        return this;
    }

    public TeacherBuilder setAge(int age) {
        mAge = age;
        return this;
    }

    public Teacher createTeacher() {
        return new Teacher(mName, mAge);
    }
}