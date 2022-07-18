package com.example.bank_app.dto;

import com.example.bank_app.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDto {


    private Long id;

    private String cardNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiredDate;

    private String csvCode;

    private CardType cardType;

    private Long accountId;



}
