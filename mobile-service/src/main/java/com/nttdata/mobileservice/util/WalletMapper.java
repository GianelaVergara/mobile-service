package com.nttdata.mobileservice.util;

import com.nttdata.mobileservice.dto.WalletDto;
import com.nttdata.mobileservice.model.Wallet;

public class WalletMapper {

    private WalletMapper() {
        throw new IllegalStateException("utility");
    }
    public static Wallet toWallet(WalletDto walletDto) {
        return Wallet.builder()
                .id(walletDto.getId())
                .documentType(walletDto.getDocumentType())
                .documentNumber(walletDto.getDocumentNumber())
                .cellPhoneNumber(walletDto.getCellPhoneNumber())
                .cellPhoneImei(walletDto.getCellPhoneImei())
                .email(walletDto.getEmail())
                .balance(walletDto.getBalance())
                .build();
    }

    public static WalletDto toWalletDto(Wallet wallet) {
        return WalletDto.builder()
                .id(wallet.getId())
                .documentType(wallet.getDocumentType())
                .documentNumber(wallet.getDocumentNumber())
                .cellPhoneNumber(wallet.getCellPhoneNumber())
                .cellPhoneImei(wallet.getCellPhoneImei())
                .email(wallet.getEmail())
                .balance(wallet.getBalance())
                .build();
    }
}
