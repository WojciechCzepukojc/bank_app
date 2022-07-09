package com.example.bank_app.models;

import javax.persistence.*;

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

    @ManyToOne
    private Customer customer;

}
