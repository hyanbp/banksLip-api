package com.banksLip.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
public class BanksLip {

    @Id
    private String id;
    private LocalDate dueDate;
    private BigDecimal totalInCents;
    private String customer;
    private String status;


    public BanksLip(String id,LocalDate dueDate, BigDecimal totalInCents, String customer, String status) {
        this.id = id;
        this.dueDate = dueDate;
        this.totalInCents = totalInCents;
        this.customer = customer;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BanksLip() {
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


    public enum Status {
        PENDING("PENDING"), PAID("PAID"), CANCELED("CANCELED");

        private String description;


        Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

    }
}

