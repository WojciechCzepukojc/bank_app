package com.example.bank_app.dto;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {


    private Long id;

    private String accountNumber;

    private String swift;

    private String iban;

    private Long customerId;

    private BigDecimal balance;




}
