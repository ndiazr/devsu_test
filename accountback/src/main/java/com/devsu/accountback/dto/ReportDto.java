package com.devsu.accountback.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportDto {
    private LocalDate date;
    private String name;
    private String accountNumber;
    private String accountType;
    private double initialBalance;
    private Boolean accountState;
    private String movementAmount;
    private double accountBalance;
}
