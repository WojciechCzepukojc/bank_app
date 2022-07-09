package com.example.bank_app.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 3)
    private String code;

    @Column(nullable = false, columnDefinition = "bigint default 100")
    private BigDecimal amount;

}
