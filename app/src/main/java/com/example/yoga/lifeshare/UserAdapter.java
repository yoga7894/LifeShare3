package com.example.yoga.lifeshare;

/**
 * Created by Yoga on 9/1/2017.
 */

public class UserAdapter {

    String address,bloodGroup,city,dob,email,name,phone,state;

    public UserAdapter(){

    }

    public UserAdapter(String address, String bloodGroup, String city, String dob, String email, String name, String phone, String state) {
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.city = city;
        this.dob = dob;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
