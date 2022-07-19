package com.example.bank_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDto {

    private Long id;

    private BigDecimal amount;

    private String recipient;

    private String recipientAccountNumber;

    private String message;

}
