package com.lec.android.a007_activity;

import java.io.Serializable;

//Intent에 담아 보내는 객체는 반드시 Serializable 되어 있어야함.
public class Person implements Serializable {
    String name;
    int age;

    public Person(){}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
