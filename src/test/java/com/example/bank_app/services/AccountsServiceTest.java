package com.example.bank_app.services;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.exceptions.ResourceNotFoundException;
import com.example.bank_app.exceptions.ResurceValidationException;
import com.example.bank_app.mappers.AccountsMapper;
import com.example.bank_app.models.Account;
import com.example.bank_app.repositories.AccountsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.times;


@SpringBootTest
class AccountsServiceTest {

    @MockBean
    private AccountsRepository accountsRepository;

    @SpyBean
    private AccountsMapper accountsMapper;

    @Autowired
    private AccountsService accountsService;


    private AccountDto accountDto;

    @BeforeEach
    void reset() {
        Mockito.reset(accountsRepository);

        accountDto = AccountDto.builder()
                .id(1L)
                .accountNumber("10256985623574125896541236")
                .swift("33025698")
                .iban("PL11256985623574125896541236")
                .balance(BigDecimal.valueOf(100))
                .customerId(1L)
                .build();
    }

    @Test
    void testCreateSucces() {
        //given
        //when
        accountsService.create(accountDto);
        //then
        Mockito.verify(accountsMapper).map(accountDto);
        Mockito.verify(accountsRepository).save(Mockito.any(Account.class));
        Mockito.verifyNoMoreInteractions(accountsMapper, accountsRepository);
    }

    @Test
    void testGetAccountSucces() {
        //given
        //when
        Optional<Account> optionalAccount = accountsRepository.findById(accountDto.getId());
        //then
        Mockito.verify(accountsRepository).findById(accountDto.getId());
        Mockito.doReturn(Optional.of(optionalAccount)).when(accountsRepository).findById(1L);
    }

    @Test
    void testUpdateAccountSucces() {
        //given
        final Long id = accountDto.getId();
        final Account expectedAccount = accountsMapper.map(accountDto);

        Mockito.when(accountsRepository.existsById(id)).thenReturn(true);

        //when
        accountsService.updateById(id, accountDto);

        //then
        Mockito.verify(accountsRepository).existsById(id);
        Mockito.verify(accountsMapper, times(2)).map(accountDto);
        Mockito.verify(accountsRepository).save(Mockito.any(Account.class));
        Mockito.verifyNoMoreInteractions(accountsMapper, accountsRepository);
    }

    @Test
    void testUpdateAccountNotFound() {
        //given
        final Long id = accountDto.getId();

        Mockito.when(accountsRepository.existsById(id)).thenReturn(false);

        //when
        Assertions.assertThrows(ResourceNotFoundException.class, ()->accountsService.updateById(id, accountDto));

        //then
        Mockito.verify(accountsRepository).existsById(id);
        Mockito.verifyNoMoreInteractions(accountsMapper, accountsRepository);
        Mockito.verifyNoInteractions(accountsMapper);
    }

    @Test
    void testUpdateAccountIdsConflict() {
        //given
        final Long id = 2L;

        Mockito.when(accountsRepository.existsById(id)).thenReturn(true);

        //when
        Assertions.assertThrows(ResurceValidationException.class, ()->accountsService.updateById(id, accountDto));

        //then
        Mockito.verify(accountsRepository).existsById(id);
        Mockito.verifyNoMoreInteractions(accountsMapper, accountsRepository);
        Mockito.verifyNoInteractions(accountsMapper);
    }

}