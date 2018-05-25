package com.banksLip.web;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.banksLip.domain.BanksLip;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBanksLipResponse extends BanksLipResponse  {


    private String id;
    private LocalDate dueDate;
    private BigDecimal totalInCents;
    private String customer;
    private String status;
    private BigDecimal fine;


    public GetBanksLipResponse(BanksLip response){
        this.id = response.getId();
        this.dueDate = response.getDueDate();
        this.totalInCents = response.getTotalInCents();
        this.customer = response.getCustomer();
        this.fine = response.getFine();
        this.status = response.getStatus();
    }

    public static  GetBanksLipResponse instance(BanksLip banksLip){
        return new GetBanksLipResponse(banksLip);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getFine() {
        return this.fine;
    }
    
    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public BigDecimal getTotalInCents() {

        return totalInCents;
    }

    public void setTotalInCents(BigDecimal totalInCents) {
        this.totalInCents = totalInCents;
    }



}