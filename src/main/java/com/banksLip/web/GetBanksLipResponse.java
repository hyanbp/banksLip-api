package com.banksLip.web;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBanksLipResponse extends BanksLipResponse  {


    private String id;
    private LocalDate dueDate;
    private BigDecimal totalInCents;
    private String customer;
    private String status;
    private BigDecimal fine;

    public GetBanksLipResponse(String id, LocalDate dueDate, BigDecimal totalInCents, String customer, BigDecimal fine, String status) {
        this.id = id;
        this.dueDate = dueDate;
        this.totalInCents = totalInCents;
        this.customer = customer;
        this.fine = this.getFine();
        this.status = status;
    }


    public GetBanksLipResponse(String response,Integer httpStatus){
        super(response,httpStatus);
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

        //double valorOriginal = 178.00;
        //double percentual = 15.0 / 100.0;
        //double valorFinal = valorOriginal + (percentual * valorOriginal);
            if(this.dueDate.isBefore(LocalDate.now())) {
            if (Period.between(this.dueDate, LocalDate.now()).getDays() < 10) {
                return getInterest(new BigDecimal(0.5),totalInCents);
            } else {
                return getInterest(new BigDecimal(1.0),totalInCents);
            }
        }
        return null;
    }

    private BigDecimal getInterest(BigDecimal percent, BigDecimal totalInCents) {
        percent = percent.divide(new BigDecimal(100));
         return percent.multiply(totalInCents);
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