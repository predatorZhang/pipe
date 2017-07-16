package com.casic.accessControl.phone.dto;

/**
 * Created by test203 on 2015/11/3.
 */
public class DataDTO {
    private String token;
    private int invalid_time;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getInvalid_time() {
        return invalid_time;
    }

    public void setInvalid_time(int invalid_time) {
        this.invalid_time = invalid_time;
    }
}
