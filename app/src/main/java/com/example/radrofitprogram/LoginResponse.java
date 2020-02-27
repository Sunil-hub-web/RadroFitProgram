package com.example.radrofitprogram;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class LoginResponse {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("data")
    @Expose
    private List<LoginArray> data = null;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<LoginArray> getData() {
        return data;
    }

    public void setData(List<LoginArray> data) {
        this.data = data;
    }

}
