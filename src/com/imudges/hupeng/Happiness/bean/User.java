package com.imudges.hupeng.Happiness.bean;

/**
 * Created by apollo on 8/22/16.
 */
public class User {
    public String user_id;
    public String phone_num;
    public String token;
    public String user_photo;
    public Integer gender;
    public Integer lover;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getLover() {
        return lover;
    }

    public void setLover(Integer lover) {
        this.lover = lover;
    }
}
