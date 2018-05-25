package com.banksLip.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banksLip.domain.BanksLip;
import com.banksLip.domain.BanksLip.Status;
import com.banksLip.web.BanksLipRequest;
import com.banksLip.web.BanksLipResponse;
import com.banksLip.web.GetBanksLipResponse;
import com.banksLip.web.GetListBanksLipResponse;

@Service
public class BanksLipServiceImpl implements BanksLipService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BanksLipServiceImpl.class);

	public static final String BANKSLIP_CHANGED = "Bankslip changed";
	public static final String BANKSLIP_PAID = "Bankslip paid";
	public static final String BANKSLIP_CREATED = "Bankslip created";
	public static final String BANKSLIP_NOT_FOUND = "Bankslip not found with the specified id";
	public static final String ERROR_NOT_FOUND_ID = "Bankslip not found with the specified id [{}]";
	public static final String INVALID_BANKSLIP = "Invalid bankslip provided.The possible reasons are: \n A field of the provided bankslip was null or with invalid values ";

	@Autowired
	private BanksLipRepository banksLipRepository;

	@Override
	public ResponseEntity<BanksLipResponse> save(BanksLipRequest request) {
		if (request == null) {
			LOGGER.error("Request is null or empty");
			return new ResponseEntity<>(BanksLipResponse.instance(INVALID_BANKSLIP), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		LOGGER.info("Created banksLip dueDate[{}], total[{}], customer [{}], status[{}]", request.getDueDate(), request.getTotalInCents(), request.getCustomer(), Status.PENDING.getDescription());
		banksLipRepository.save(new BanksLip(UUID.randomUUID().toString(), request.getDueDate(), request.getTotalInCents(), request.getCustomer()));
		return new ResponseEntity<>(BanksLipResponse.instance(BANKSLIP_CREATED), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<BanksLipResponse> process(String reqStatus, String id) {
		LOGGER.info("Process bankslip status[{}] by id[{}]", reqStatus, id);
		Optional<BanksLip> banksLip = findById(id);
		if (banksLip.isPresent()) {
			BanksLip bl = banksLip.get();
			bl.processStatus(reqStatus);
			banksLipRepository.save(bl);
			LOGGER.info("Changed status banksLip id [{}] status[{}]",id, reqStatus);
			return new ResponseEntity<>(BanksLipResponse.instance(BANKSLIP_CHANGED), HttpStatus.OK);
		}
		LOGGER.error(ERROR_NOT_FOUND_ID, id);
		return new ResponseEntity<>(BanksLipResponse.instance(BANKSLIP_NOT_FOUND), HttpStatus.NOT_FOUND);

	}


	@Override
	public ResponseEntity<GetBanksLipResponse> getBanksLipById(String id) {
		LOGGER.info("getBanksLip by id [{}]", id);
		Optional<BanksLip> banksLip = findById(id);
		if (!banksLip.isPresent()) {
			LOGGER.error(ERROR_NOT_FOUND_ID, id);
			return new ResponseEntity(BanksLipResponse.instance(BANKSLIP_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		 LOGGER.info(banksLip.get().toString());
		return new ResponseEntity(GetBanksLipResponse.instance(banksLip.get()), HttpStatus.OK);
	}


	@Override
	public GetListBanksLipResponse listAll() {
		LOGGER.info("List all BanksLip");
		List<BanksLip> banksLipList = banksLipRepository.getAll();
		return new GetListBanksLipResponse(banksLipList);
	}

	public Optional<BanksLip> findById(String id) {
		return banksLipRepository.findById(id);
	}

}
