package com.example.bank_app.services;

import com.example.bank_app.dto.CardDto;
import com.example.bank_app.enums.CardType;
import com.example.bank_app.exceptions.ResourceNotFoundException;
import com.example.bank_app.exceptions.ResurceValidationException;
import com.example.bank_app.mappers.CardsMapper;
import com.example.bank_app.models.Card;
import com.example.bank_app.repositories.CardsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.times;


@SpringBootTest
class CardsServiceTest {

    @MockBean
    private CardsRepository cardsRepository;

    @SpyBean
    private CardsMapper cardsMapper;

    @Autowired
    private CardsService cardsService;

    private CardDto cardDto;

    @BeforeEach
    void reset() {
        Mockito.reset(cardsRepository);

        cardDto = CardDto.builder()
                .id(1L)
                .cardNumber("4423658745235625")
                .expiredDate(LocalDate.ofEpochDay(2025 - 07 - 01))
                .csvCode("333")
                .cardType(CardType.valueOf("CREDIT"))
                .accountId(1L)
                .build();
    }

    @Test
    void testCreateCardSucces() {
        //given
        //when
        cardsService.create(cardDto);
        //then
        Mockito.verify(cardsMapper).map(cardDto);
        Mockito.verify(cardsRepository).save(Mockito.any(Card.class));
        Mockito.verifyNoMoreInteractions(cardsMapper, cardsRepository);
    }

    @Test
    void testGetCardSucces() {
        //given
        //when
        Optional<Card> optionalCard = cardsRepository.findById(cardDto.getId());
        //then
        Mockito.verify(cardsRepository).findById(cardDto.getId());
        Mockito.doReturn(Optional.of(optionalCard)).when(cardsRepository).findById(1L);
    }

    @Test
    void testUpdateCardSucces() {
        //given
        final Long id = cardDto.getId();
        final Card expectedCard = cardsMapper.map(cardDto);

        Mockito.when(cardsRepository.existsById(id)).thenReturn(true);

        //when
        cardsService.updateById(id, cardDto);

        //then
        Mockito.verify(cardsRepository).existsById(id);
        Mockito.verify(cardsMapper, times(2)).map(cardDto);
        Mockito.verify(cardsRepository).save(Mockito.any(Card.class));
        Mockito.verifyNoMoreInteractions(cardsMapper, cardsRepository);
    }

    @Test
    void testUpdateCardNotFound() {
        //given
        final Long id = cardDto.getId();

        Mockito.when(cardsRepository.existsById(id)).thenReturn(false);

        //when
        Assertions.assertThrows(ResourceNotFoundException.class, ()->cardsService.updateById(id, cardDto));

        //then
        Mockito.verify(cardsRepository).existsById(id);
        Mockito.verifyNoMoreInteractions(cardsMapper, cardsRepository);
        Mockito.verifyNoInteractions(cardsMapper);
    }

    @Test
    void testUpdateCardIdsConflict() {
        //given
        final Long id = 2L;

        Mockito.when(cardsRepository.existsById(id)).thenReturn(true);

        //when
        Assertions.assertThrows(ResurceValidationException.class, ()->cardsService.updateById(id, cardDto));

        //then
        Mockito.verify(cardsRepository).existsById(id);
        Mockito.verifyNoMoreInteractions(cardsMapper, cardsRepository);
        Mockito.verifyNoInteractions(cardsMapper);
    }

    @Test
    void testDeleteCardSucces() {
        //given
        Card card = cardsMapper.map(cardDto);
        //when
        cardsService.deleteById(card.getId());
        //then
        Mockito.verify(cardsMapper).map(cardDto);
        Mockito.verify(cardsRepository).deleteById(card.getId());
        Mockito.verifyNoMoreInteractions(cardsMapper, cardsRepository);
    }

    }