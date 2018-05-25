package com.banksLip.application;

import org.springframework.http.ResponseEntity;

import com.banksLip.web.BanksLipRequest;
import com.banksLip.web.BanksLipResponse;
import com.banksLip.web.GetBanksLipResponse;
import com.banksLip.web.GetListBanksLipResponse;

public interface BanksLipService {

    ResponseEntity<BanksLipResponse> save(BanksLipRequest request);

    ResponseEntity<BanksLipResponse> process(String status,String id);

    ResponseEntity<GetBanksLipResponse> getBanksLipById(String id);

    GetListBanksLipResponse listAll();


}
