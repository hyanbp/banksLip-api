package com.banksLip.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.banksLip.web.GetBanksLipResponse;

public class BanksLipTest {
	
	
    private static final String DEZ_MIL = "10000";
	private static final String CUSTOMER = "testeCustomer";

	
    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }
	  
	  @Test
	    public void getBanksLipByFineStatusPendingThenDaysExpired() {
	    	String id = UUID.randomUUID().toString();
	    	BanksLip bl = new BanksLip(id, LocalDate.now().minusDays(10L), new BigDecimal(DEZ_MIL), CUSTOMER);
	    	
	    	GetBanksLipResponse resp = new GetBanksLipResponse(bl);
	    	BigDecimal expected = new BigDecimal("100.00");
	    	assertEquals(expected, resp.getFine());
	    }
	  
	  @Test
	    public void getBanksLipByFineStatusPendingFiveDaysExpired() {
	    	String id = UUID.randomUUID().toString();
	    	BanksLip bl = new BanksLip(id, LocalDate.now().minusDays(5L), new BigDecimal(DEZ_MIL),CUSTOMER);
	    	
	    	GetBanksLipResponse resp = new GetBanksLipResponse(bl);
	    	BigDecimal expected = new BigDecimal("50.000");
	    	assertEquals(expected, resp.getFine());
	    }
	  

}
