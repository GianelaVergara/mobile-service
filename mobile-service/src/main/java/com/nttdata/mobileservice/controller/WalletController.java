package com.nttdata.mobileservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nttdata.mobileservice.service.IServiceWallet;
import com.nttdata.mobileservice.dto.WalletDto;
import java.util.List;
import com.nttdata.mobileservice.model.Payment;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallets")
public class WalletController {
    private final IServiceWallet iServiceWallet;

    @GetMapping("/all")
    public ResponseEntity<List<WalletDto>> getAll() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(iServiceWallet.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(iServiceWallet.getById(id));
    }

    @PostMapping
    public ResponseEntity<WalletDto> register(@RequestBody WalletDto wallet) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(iServiceWallet.register(wallet));
    }

    @PutMapping("/payments")
    public ResponseEntity<WalletDto> doPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(iServiceWallet.doPayment(payment));
    }
}
