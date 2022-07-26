package com.nttdata.mobileservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    private String id;
    private String documentType;
    private String documentNumber;
    private String cellPhoneNumber;
    private String cellPhoneImei;
    private String email;
    private Double balance;
}
