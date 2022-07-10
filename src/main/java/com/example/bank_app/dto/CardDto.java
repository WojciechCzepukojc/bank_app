package com.example.bank_app.dto;

import com.example.bank_app.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDto {


    private Long id;

    private String cardNumber;

    private LocalDate expiredDate;

    private LocalDate csvCode;

    private CardType cardType;



}
