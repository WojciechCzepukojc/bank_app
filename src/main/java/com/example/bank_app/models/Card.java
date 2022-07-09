package com.example.bank_app.models;

import com.example.bank_app.enums.CardType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 16)
    private String cardNumber;

    @Column(nullable = false, length = 16)
    private LocalDate expiredDate;

    @Column(nullable = false, length = 3)
    private LocalDate csvCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private CardType cardType;

    @ManyToOne
    private Account account;

}
