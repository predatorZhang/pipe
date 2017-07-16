package com.casic.accessControl.phone.dto;

/**
 * Created by test203 on 2015/11/3.
 */
public class LoginResult {
    private ResultDTO result;
    private DataDTO data;

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
