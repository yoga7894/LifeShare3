package com.example.yoga.lifeshare;

/**
 * Created by Yoga on 8/29/2017.
 */

public class Blog {



    String Name,place,posted,Bgroup;
    String Email;
    String Phone;
    String Desc;

    public Blog(String place, String name, String posted, String bgroup, String email, String phone, String desc) {
        this.place = place;
        Name = name;
        this.posted = posted;
        Bgroup = bgroup;
        Email = email;
        Phone = phone;
        Desc = desc;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }



    public Blog()
    {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

    public String getBgroup() {
        return Bgroup;
    }

    public void setBgroup(String bgroup) {
        Bgroup = bgroup;
    }
}
