package com.banksLip.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BanksLip {

	@Id
	private String id;
	private LocalDate dueDate;
	private BigDecimal totalInCents;
	private String customer;
	private String status;

	private BanksLip() {
		// contructor for hibernate
	}

	public BanksLip(String id, LocalDate dueDate, BigDecimal totalInCents, String customer) {
		this.id = id;
		this.dueDate = dueDate;
		this.totalInCents = totalInCents;
		this.customer = customer;
		this.status = Status.PENDING.getDescription();
	}

	public String getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public BigDecimal getTotalInCents() {
		return totalInCents;
	}

	public String getCustomer() {
		return customer;
	}

	public void processStatus(String status) {
		this.status = Status.valueOf(status).getDescription();
	}

	private BigDecimal getInterest(BigDecimal percent, BigDecimal totalInCents) {
		percent = percent.divide(new BigDecimal(100));
		return percent.multiply(totalInCents);
	}

	public BigDecimal getFine() {
		if (this.dueDate.isBefore(LocalDate.now())) {
			if (Period.between(this.dueDate, LocalDate.now()).getDays() < 10) {
				return getInterest(new BigDecimal(0.5), totalInCents);
			} else {
				return getInterest(new BigDecimal(1.0), totalInCents);
			}
		}
		return null;
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

	@Override
	public String toString() {
		return "BanksLip [id=" + id + ", dueDate=" + dueDate + ", totalInCents=" + totalInCents + ", customer="
				+ customer + ", status=" + status + "]";
	}
	
	
}
