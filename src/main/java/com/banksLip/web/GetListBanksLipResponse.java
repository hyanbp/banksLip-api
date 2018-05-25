package com.banksLip.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.banksLip.domain.BanksLip;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetListBanksLipResponse extends BanksLipResponse {


    private List<GetBanksLipResponse> banksLipList;

    public GetListBanksLipResponse() {
    }



    public GetListBanksLipResponse(List<BanksLip> banksLipList) {
        if(CollectionUtils.isNotEmpty(banksLipList)){
            List<GetBanksLipResponse> responses = new ArrayList<>();
            for (BanksLip baksLip: banksLipList) {
                responses.add(new GetBanksLipResponse(baksLip));
            }
            this.banksLipList = responses;
        }
    }

    public List<GetBanksLipResponse> getBanksLipList() {
        return banksLipList;
    }

    public void setBanksLipList(List<GetBanksLipResponse> banksLipList) {
        this.banksLipList = banksLipList;
    }
}
