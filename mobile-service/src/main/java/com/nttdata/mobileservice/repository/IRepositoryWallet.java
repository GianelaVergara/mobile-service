package com.nttdata.mobileservice.repository;

import com.nttdata.mobileservice.model.Wallet;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


public interface IRepositoryWallet  extends CrudRepository<Wallet, String> {
    Optional<Wallet> findByCellPhoneNumber(String cellPhoneNumber);
}
