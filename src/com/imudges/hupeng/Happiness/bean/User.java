package com.imudges.hupeng.Happiness.bean;

/**
 * Created by apollo on 8/22/16.
 */
public class User {
    public Integer id;
    public String phone_num;
    public String token;
    public String user_photo;
    public Integer gender;
    public Integer lover_id;
    public String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getLover_id() {
        return lover_id;
    }

    public void setLover_id(Integer lover_id) {
        this.lover_id = lover_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
