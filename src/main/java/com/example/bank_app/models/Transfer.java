package com.example.bank_app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "bigint default 100")
    private BigDecimal amount;

    @Column(nullable = false, length = 100)
    private String recipient;

    @Column(nullable = false, length = 26)
    private String recipientAccountNumber;

    @Column(nullable = false, length = 50)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

}
