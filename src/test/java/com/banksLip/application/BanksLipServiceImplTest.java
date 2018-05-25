package com.banksLip.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.banksLip.domain.BanksLip;
import com.banksLip.domain.BanksLip.Status;
import com.banksLip.web.BanksLipController;
import com.banksLip.web.BanksLipRequest;
import com.banksLip.web.BanksLipResponse;
import com.banksLip.web.GetBanksLipResponse;
import com.banksLip.web.GetListBanksLipResponse;

public class BanksLipServiceImplTest {

    private static final String CUSTOMER = "testeCustomer";

	@InjectMocks BanksLipServiceImpl banksLipService;

    @Mock BanksLipRepository banksLipRepository;
    
    @Mock GetListBanksLipResponse getListBanksLipResponse;
    
    @InjectMocks BanksLipController banksLipController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void createBanksLipSuccess() {
       assertEquals(HttpStatus.CREATED.value(), banksLipService.save(buildRequest()).getStatusCodeValue());
    }

    @Test
    public void createBanksLipRequestNull() {
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), banksLipService.save(null).getStatusCodeValue());
    }



    @Test
    public void processSuccess() {
    	String id = UUID.randomUUID().toString();
    	BanksLip banksLip = new BanksLip(id, LocalDate.now(), BigDecimal.ONE,CUSTOMER);
    	Mockito.when(banksLipService.findById(id)).thenReturn(Optional.of(banksLip));
        ResponseEntity<BanksLipResponse> expetecd = new ResponseEntity<>(HttpStatus.OK);
    	assertEquals(expetecd.getStatusCode(), banksLipService.process(Status.PAID.getDescription(), id).getStatusCode());
    }
    
    @Test
    public void processNotFoundId() {
    	String id = UUID.randomUUID().toString();
    	Mockito.when(banksLipService.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<BanksLipResponse> expetecd = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	assertEquals(expetecd.getStatusCode(), banksLipService.process(Status.PAID.getDescription(), id).getStatusCode());
    }

    @Test
    public void getBanksLipByIdSucces() {
    	String id = UUID.randomUUID().toString();
    	BanksLip bl = new BanksLip(id, buildRequest().getDueDate(), buildRequest().getTotalInCents(), buildRequest().getCustomer());
    	Mockito.when(banksLipService.findById(id)).thenReturn(Optional.of(bl));
        ResponseEntity<BanksLipResponse> expetecd = new ResponseEntity<>(HttpStatus.OK);
    	assertEquals(expetecd.getStatusCode(), banksLipService.getBanksLipById(id).getStatusCode());
    }
    
    
    @Test
    public void getBanksLipByIdNotFound() {
    	String id = UUID.randomUUID().toString();
    	Mockito.when(banksLipService.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<BanksLipResponse> expetecd = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	assertEquals(expetecd.getStatusCode(), banksLipService.getBanksLipById(id).getStatusCode());
    }
    

	private List<BanksLip> buildBanksLipList(String id) {
		BanksLip bl = new BanksLip(id, buildRequest().getDueDate(), buildRequest().getTotalInCents(), buildRequest().getCustomer());
    	List<BanksLip> banksLips = new ArrayList<>();
    	banksLips.add(bl);
		return banksLips;
	}
	
	  @Test
	    public void getBanksLipByFineStatusPending() {
	    	String id = UUID.randomUUID().toString();
	    	BanksLip bl = new BanksLip(id, buildRequest().getDueDate(), buildRequest().getTotalInCents(), buildRequest().getCustomer());
	    	
	    	GetBanksLipResponse resp = new GetBanksLipResponse(bl);
	    	assertNull(resp.getFine());
	    }
	  
	  
    private BanksLipRequest buildRequest(){
        BanksLipRequest blr = new BanksLipRequest();
        blr.setDueDate(LocalDate.now());
        blr.setCustomer("TESTE CUSTOMER");
        blr.setStatus(Status.PENDING.getDescription());
        blr.setTotalInCents(new BigDecimal(10000));
        return  blr;
    }


}