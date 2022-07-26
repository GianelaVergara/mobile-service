package com.nttdata.mobileservice.service;
import java.util.List;
import com.nttdata.mobileservice.model.Payment;
import com.nttdata.mobileservice.dto.WalletDto;


public interface IServiceWallet {
    List<WalletDto> getAll();

    WalletDto getById(String id);

    WalletDto register(WalletDto wallet);

    WalletDto doPayment(Payment payment);
}
