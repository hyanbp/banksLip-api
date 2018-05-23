package com.banksLip.web;

import java.math.BigDecimal;

public class BanksLipResponse {

    private String response;
    private Integer httpStatus;

    public BanksLipResponse(String response){
        this.response = response;
    }

    public BanksLipResponse(String response, Integer httpStatus){
        this.response = response;
        this.httpStatus = httpStatus;
    }

    public BanksLipResponse() {
    }

    public static  BanksLipResponse instance(String response){
        return new BanksLipResponse(response);
    }

    public String getResponse(){
        return this.response;
    }
}


