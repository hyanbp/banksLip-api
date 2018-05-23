package com.banksLip.web;

import com.banksLip.application.BanksLipService;
import com.banksLip.domain.BanksLipException;
import com.banksLip.web.BanksLipRequest;
import com.banksLip.web.BanksLipResponse;
import com.banksLip.web.GetBanksLipResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping("/rest/bankslip")
public class BanksLipController {

    public static final String BANKSLIP_CREATED = "Bankslip created";
    public static final String INVALID_BANKSLIP = "Invalid bankslip provided.The possible reasons are: \n A field of the provided bankslip was null or with invalid values ";
    public static final String BAD_REQUEST = "Bankslip not provided in the request body";
    public static final String BANKSLIP_NOT_FOUND = "Bankslip not found with the specified id";

    @Autowired
    private BanksLipService banksLipService;



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BanksLipResponse> create(@RequestBody  BanksLipRequest request){
        try {
            banksLipService.save(request.getDueDate(), request.getTotalInCents(), request.getCustomer(), request.getStatus());
            return new ResponseEntity<>(BanksLipResponse.instance(BANKSLIP_CREATED), HttpStatus.OK);
        }catch (BanksLipException ble){
            return new ResponseEntity<>(BanksLipResponse.instance(BANKSLIP_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value="/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<BanksLipResponse> process(@RequestBody  BanksLipRequest request  , @PathVariable("id") String id){
        try {
            return banksLipService.process(request.getStatus(), id);
        }catch (BanksLipException ble){
            return new ResponseEntity<>(BanksLipResponse.instance(BANKSLIP_NOT_FOUND), HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value="/{id}" , method = RequestMethod.GET, produces = "application/json")
    public GetBanksLipResponse fullDetail(@NotNull  @PathVariable(value = "id")  String id){
        try {
           return banksLipService.fullDetail(id);
        }catch (BanksLipException ble){
            return new GetBanksLipResponse(BANKSLIP_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

    }

}
