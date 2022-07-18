package com.example.bank_app.repositories;

import com.example.bank_app.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Card, Long> {

    List<Card> findCardByAccount_Id(Long accountId);

}
