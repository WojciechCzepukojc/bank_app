package com.example.bank_app.services;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.dto.CardDto;
import com.example.bank_app.exceptions.ResourceNotFoundException;
import com.example.bank_app.exceptions.ResurceValidationException;
import com.example.bank_app.mappers.CardsMapper;
import com.example.bank_app.models.Account;
import com.example.bank_app.models.Card;
import com.example.bank_app.models.Customer;
import com.example.bank_app.repositories.CardsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CardDto> getByAccountId (Long id){
        log.info("Fetching cards by account id '{}'", id);
        List<Card> cardsByAccoundId = cardsRepository.findCardByAccount_Id(id);
        return cardsByAccoundId.stream()
                .map(card -> cardsMapper.map(card))
                .collect(Collectors.toList());
    }

    public void updateById(Long id, CardDto cardDto){
        log.info("Updating card with id '{}'. New card: '{}'", id, cardDto);
        if (!cardsRepository.existsById(id)) {
            log.warn("Card with id '{}' not found", id);
            throw getCardtNotFoundException(id);
        }

        Long cardDtoId = cardDto.getId();
        if (!id.equals(cardDtoId)) {
            log.warn("Id parameter '{}' and card id '{}' does not match", id, cardDtoId);
            throw new ResurceValidationException(
                    String.format("Id parameter '%s' and card id '%s' does not match",
                            id, cardDtoId)
            );
        }

        Card card = cardsMapper.map(cardDto);
        cardsRepository.save(card);
    }

    private ResourceNotFoundException getCardtNotFoundException(Long id) {
        return new ResourceNotFoundException(String.format("Card with id '%s' not found", id));
    }



}
