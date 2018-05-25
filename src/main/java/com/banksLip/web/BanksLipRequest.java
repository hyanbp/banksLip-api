package com.banksLip.web;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;
import java.time.LocalDate;


public class BanksLipRequest {


    private LocalDate dueDate;
    private BigDecimal totalInCents;
    private String customer;
    private String status;


    @JsonCreator
    public BanksLipRequest(LocalDate dueDate, BigDecimal totalInCents, String customer, String status) {
        this.dueDate = dueDate;
        this.totalInCents = totalInCents;
        this.customer = customer;
        this.status = status;
    }

    public BanksLipRequest() {

    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getTotalInCents() {
        return totalInCents;
    }

    public void setTotalInCents(BigDecimal totalInCents) {
        this.totalInCents = totalInCents;
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
}

