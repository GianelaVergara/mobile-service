package com.nttdata.mobileservice.model;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)

public class Payment {
    private String sourceCellPhoneNumber;
    private String targetCellPhoneNumber;
    private Double amount;
}
