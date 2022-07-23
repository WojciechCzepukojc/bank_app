package com.example.bank_app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 26)
    private String accountNumber;

    @Column(nullable = false, length = 8)
    private String swift;

    @Column(nullable = false, length = 28)
    private String iban;

    @Column(columnDefinition = "bigint default 1000")
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<Card> cards;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<Transfer> transfers;

}
