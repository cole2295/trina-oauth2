package com.trinasolar.oauth2.server.user.domain;

import java.io.Serializable;

/**
 * Created by zhm on 16-9-13.
 */
public class UserInfo implements Serializable{
    private static final long serialVersionUID = 8074225596915336466L;
    private Integer id;
    private String username;
    private String mobile;
    private String email;
    private String password;
    private String role;
    private String urls;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
    public void clearPassword(){
        this.setPassword("");
    }
}
