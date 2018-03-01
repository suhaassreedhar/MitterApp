package com.suhaas.mitterapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by suhaas on 1/3/18.
 */

public class MitterResponse {

    @SerializedName("msg")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
