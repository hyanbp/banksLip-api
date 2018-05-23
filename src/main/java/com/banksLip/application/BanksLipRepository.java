package com.banksLip.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banksLip.domain.BanksLip;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BanksLipRepository  extends JpaRepository<BanksLip, String> {

    Optional<BanksLip> findById(UUID id);
}
