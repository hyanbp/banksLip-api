package com.banksLip.web;

public class BanksLipResponse {

    private String response;


    public BanksLipResponse(String response){
        this.response = response;
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


