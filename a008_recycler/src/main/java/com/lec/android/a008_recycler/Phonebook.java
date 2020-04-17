package com.lec.android.a008_recycler;

import java.io.Serializable;

public class Phonebook implements Serializable { //Serializable Intent 새로운 액티비티 만드려고 필요
    //사진,이름,전화번호,이메일을 담아야함.
    //사진,이따가 resource 쓸 꺼라서
    int photo;
    String name;
    String phone;
    String email;

    public Phonebook(){}

    public Phonebook(int photo, String name, String phone, String email) {
        this.photo = photo;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getPhoto() {return photo;}
    public void setPhoto(int photo) {this.photo = photo;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
}
