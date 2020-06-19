package com.vg.basic.spring.aop.model;

public class UserModel {

    private Integer userId;

    private String userName;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString(){
        return "{" + this.userId + "," + this.userName + "}";
    }
}
