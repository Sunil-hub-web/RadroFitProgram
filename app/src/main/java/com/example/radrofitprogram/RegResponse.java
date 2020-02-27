package com.example.radrofitprogram;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class RegResponse {
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("user")
    @Expose
    private String user;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
