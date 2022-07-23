package com.example.bank_app.services;

import com.example.bank_app.dto.TransferDto;
import com.example.bank_app.exceptions.WrongAmoundException;
import com.example.bank_app.mappers.TransfersMapper;
import com.example.bank_app.models.Account;
import com.example.bank_app.models.Transfer;
import com.example.bank_app.repositories.AccountsRepository;
import com.example.bank_app.repositories.TransfersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransfersService {


    private final TransfersRepository transfersRepository;

    private final TransfersMapper transfersMapper;

    private final AccountsService accountsService;

    private final AccountsRepository accountsRepository;

    public void create(TransferDto transferDto, Long id){
        log.info("Creating new transfer '{}'", transferDto);
        Account account = accountsService.getAccount(id);
        Transfer transfer = transfersMapper.map(transferDto);
        int i = account.getBalance().compareTo(transfer.getAmount());
            if (i >= 1){
                transfersRepository.save(transfer);
                BigDecimal newBalance = account.getBalance().subtract(transfer.getAmount());
                account.setBalance(newBalance);
                accountsRepository.save(account);
                log.debug("Transfer sent");
            }else {
                throw getWrongAmoundException();
            }
    }
    private WrongAmoundException getWrongAmoundException() {
        return new WrongAmoundException("Insufficient funds in the Account");
    }
}
