package com.banksLip.domain;

import org.springframework.http.HttpStatus;

import com.banksLip.web.BanksLipResponse;

public class BanksLipException  extends  RuntimeException {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BanksLipException(BanksLipResponse instance, HttpStatus notFound) {
    }

   
}
