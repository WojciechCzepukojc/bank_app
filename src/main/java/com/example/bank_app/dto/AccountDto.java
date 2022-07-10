package com.example.bank_app.dto;

import lombok.*;

import javax.persistence.*;
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


    private CustomerDto customer;




}
