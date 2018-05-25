package com.banksLip.web;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banksLip.application.BanksLipService;
import com.banksLip.domain.BanksLipException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest/bankslip")
public class BanksLipController {

    public static final String BAD_REQUEST = "Bankslip not provided in the request body";
    public static final String BANKSLIP_NOT_FOUND = "Bankslip not found with the specified id";
	public static final String BANKSLIP_CREATED = "Bankslip created";
	public static final String BANKSLIP_CHANGED = "Bankslip changed";
	




    @Autowired
    private BanksLipService banksLipService;


    
	@ApiOperation(value = "Criação de um boleto bancário")
	@ApiResponses(value = { @ApiResponse(code = 201, message = BANKSLIP_CREATED),
							@ApiResponse(code = 400, message = BAD_REQUEST),
							@ApiResponse(code = 404, message = BANKSLIP_NOT_FOUND)})
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BanksLipResponse> create(@RequestBody  BanksLipRequest request){
        try {
            return banksLipService.save(request);
        }catch (BanksLipException ble){
            return new ResponseEntity<>(BanksLipResponse.instance(BANKSLIP_NOT_FOUND), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(BanksLipResponse.instance(BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }
	
	
	@ApiOperation(value = "Operação de Pagamento ou Cancelamento de um boleto bancário")
	@ApiResponses(value = { @ApiResponse(code = 200, message = BANKSLIP_CHANGED ),
							@ApiResponse(code = 404, message = BANKSLIP_NOT_FOUND)})
    
	@RequestMapping(value="/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<BanksLipResponse> process(@RequestBody  BanksLipRequest request  , @PathVariable("id") String id){
        try {
            return banksLipService.process(request.getStatus(), id);
        }catch (BanksLipException ble){
            return new ResponseEntity<>(BanksLipResponse.instance(BANKSLIP_NOT_FOUND), HttpStatus.NOT_FOUND);
        }

    }
	
	@ApiOperation(value = "Operação de detalhamento de um boleto bancário")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK" ),
							@ApiResponse(code = 404, message = BANKSLIP_NOT_FOUND)})
	
    @RequestMapping(value="/{id}" , method = RequestMethod.GET)
    public  ResponseEntity<GetBanksLipResponse> getBanksLip(@NotNull  @PathVariable(value = "id")  String id)  {
        try {
             return banksLipService.getBanksLipById(id);
        } catch (BanksLipException ble) {
           return new ResponseEntity(GetBanksLipResponse.instance(BANKSLIP_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

	@ApiOperation(value = "Operação de consulta de todos os boletos bancários disponíveis")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK" ),
							@ApiResponse(code = 404, message = BANKSLIP_NOT_FOUND)})
	
        @RequestMapping(method = RequestMethod.GET, produces = "application/json")
        public GetListBanksLipResponse listBanksLips() {
                return  banksLipService.listAll();

        }

    }
