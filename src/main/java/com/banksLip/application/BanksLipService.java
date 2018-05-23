package com.banksLip.application;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.banksLip.web.BanksLipResponse;
import com.banksLip.web.GetBanksLipResponse;

public interface BanksLipService {

    void save(LocalDate dueDate, BigDecimal total, String customer, String status);

    ResponseEntity<BanksLipResponse> process(String status,String id);

     GetBanksLipResponse fullDetail(String id);
}
