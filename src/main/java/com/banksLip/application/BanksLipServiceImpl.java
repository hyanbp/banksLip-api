package com.banksLip.application;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import com.banksLip.domain.BanksLip.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banksLip.domain.BanksLip;
import com.banksLip.domain.BanksLipException;
import com.banksLip.web.BanksLipResponse;
import com.banksLip.web.GetBanksLipResponse;

import static com.banksLip.domain.BanksLip.Status.*;

@Service
public class BanksLipServiceImpl implements BanksLipService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BanksLipServiceImpl.class);

	public static final String BANKSLIP_CANCELED = "Bankslip canceled";
	public static final String BANKSLIP_PAID = "Bankslip paid";

	@Autowired
	BanksLipRepository banksLipRepository;

	@Override
	public void save(LocalDate dueDate, BigDecimal total, String customer, String status) {
		LOGGER.info("Created banksLip dueDate[{}], total[{}], customer [{}], status[{}]", dueDate,total,customer,status);
		banksLipRepository.save(new BanksLip(UUID.randomUUID().toString(),dueDate, total, customer, status));
	}

	@Override
	public ResponseEntity<BanksLipResponse> process(String reqStatus, String id) {
		Optional<BanksLip> banksLip = findById(id);
		if (!banksLip.isPresent()) {
			throw new BanksLipException("BanksLip NOT FOUND");
		}
		switch (Status.valueOf(reqStatus)) {
            case CANCELED:
			canceled(banksLip.get());
			return new ResponseEntity<>(BanksLipResponse.instance(BANKSLIP_CANCELED), HttpStatus.CREATED);
            case PENDING:
                break;
            case PAID:
			payment(banksLip.get());
			return new ResponseEntity<>(BanksLipResponse.instance(BANKSLIP_PAID), HttpStatus.CREATED);
            default:
			break;
		}
		return null;

	}

	private void canceled(BanksLip banksLip) {
		banksLip.setStatus(CANCELED.getDescription());
		banksLipRepository.save(banksLip);
	}

	private void payment(BanksLip banksLip) {
		banksLip.setStatus(PAID.getDescription());
		banksLipRepository.save(banksLip);
	}

	@Override
	public GetBanksLipResponse fullDetail(String id) {
		LOGGER.info("FullDetail by id [{}]", id);
		Optional<BanksLip> banksLip = findById(id);
		if (!banksLip.isPresent()) {
			return null;
		}
		return new GetBanksLipResponse(banksLip.get().getId(), banksLip.get().getDueDate(),  banksLip.get().getTotalInCents(), banksLip.get().getCustomer(), new BigDecimal(0), banksLip.get().getStatus());
	}

	public Optional<BanksLip> findById(String id) {
		return banksLipRepository.findById(id);
	}

}
