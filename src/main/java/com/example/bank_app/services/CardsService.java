package com.example.bank_app.services;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.dto.CardDto;
import com.example.bank_app.mappers.CardsMapper;
import com.example.bank_app.models.Account;
import com.example.bank_app.models.Card;
import com.example.bank_app.models.Customer;
import com.example.bank_app.repositories.CardsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardsService {

    private final CardsRepository cardsRepository;

    private final CardsMapper cardsMapper;

    private final AccountsService accountsService;

    public void create (CardDto cardDto){
        log.info("Creating card '{}'", cardDto);
        Card card = cardsMapper.map(cardDto);
        Account account = accountsService.getAccount(cardDto.getAccountId());
        card.setAccount(account);
        cardsRepository.save(card);
        log.debug("Card created succesfully");
    }


}
