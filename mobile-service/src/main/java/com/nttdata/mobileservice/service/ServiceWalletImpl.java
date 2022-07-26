package com.nttdata.mobileservice.service;

import com.nttdata.mobileservice.repository.IRepositoryWallet;
import  com.nttdata.mobileservice.dto.WalletDto;
import  com.nttdata.mobileservice.producer.KafkaStringProducer;
import com.nttdata.mobileservice.model.Wallet;
import com.nttdata.mobileservice.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.nttdata.mobileservice.util.WalletMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceWalletImpl implements IServiceWallet{
    private final IRepositoryWallet iRepositoryWallet;
    private final KafkaStringProducer kafkaStringProducer;

    @Override
    public List<WalletDto> getAll() {
        kafkaStringProducer.sendMessage("Proceso existoso");
        List<Wallet> wallets = new ArrayList<>();
        iRepositoryWallet.findAll().foreach(wallets::add);
        return wallets.stream().map(WalletMapper::toWalletDto).collect(Collectors.toList());
    }

    @Override
    @Cacheable("walletCache")
    public WalletDto getById(String id) {
        log.info("Retrieving wallet with id {}", id);
        return iRepositoryWallet.findById(id)
                .map(WalletMapper::toWalletDto)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    @Override
    public WalletDto register(WalletDto wallet) {
        return WalletMapper.toWalletDto(iRepositoryWallet.save(WalletMapper.toWallet(wallet)));
    }

    @Override
    public WalletDto doPayment(Payment payment) {
        Wallet sourceWallet = iRepositoryWallet.findByCellPhoneNumber(payment.getSourceCellPhoneNumber())
                .orElseThrow(() -> new DomainException(HttpStatus.BAD_REQUEST, "Source cell phone number not found"));
        Wallet targetWallet = iRepositoryWallet.findByCellPhoneNumber(payment.getTargetCellPhoneNumber())
                .orElseThrow(() -> new DomainException(HttpStatus.BAD_REQUEST, "Target cell phone number not found"));

        if (sourceWallet.getBalance() < payment.getAmount()) {
            throw new DomainException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }
        Wallet updatedSourceWallet = iRepositoryWallet.save(sourceWallet.toBuilder().balance(sourceWallet.getBalance() - payment.getAmount()).build());
        iRepositoryWallet.save(targetWallet.toBuilder().balance(targetWallet.getBalance() + payment.getAmount()).build());
        return WalletMapper.toWalletDto(updatedSourceWallet);
    }
}
