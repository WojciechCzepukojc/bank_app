package com.example.bank_app.models;

import com.example.bank_app.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 16)
    private String cardNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, length = 10)
    private LocalDate expiredDate;

    @Column(nullable = false, length = 3)
    private String csvCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private CardType cardType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

}
