package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;

import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.User;

import java.util.List;

public class AuthTokenWithUserInfo {

    private String token;
    private User userInfo;

    public AuthTokenWithUserInfo(String token) {
        super();
        this.token = token;
    }

    public AuthTokenWithUserInfo(String token, User userInfo) {
        super();
        this.token = token;
        this.userInfo = userInfo;
    }

    public AuthTokenWithUserInfo() {
        super();
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public User getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }
}
