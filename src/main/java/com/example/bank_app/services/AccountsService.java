package com.example.bank_app.services;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.exceptions.ResourceNotFoundException;
import com.example.bank_app.exceptions.ResurceValidationException;
import com.example.bank_app.mappers.AccountsMapper;
import com.example.bank_app.mappers.CustomersMapper;
import com.example.bank_app.models.Account;
import com.example.bank_app.models.Customer;
import com.example.bank_app.repositories.AccountsRepository;
import com.example.bank_app.repositories.CustomersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsRepository accountsRepository;

    private final AccountsMapper accountsMapper;

    private final CustomersService customersService;


  public void create (AccountDto accountDto){
    log.info("Creating account '{}'", accountDto);
    Account account = accountsMapper.map(accountDto);
      Customer customer = customersService.getCustomer(accountDto.getCustomerId());
      account.setCustomer(customer);
      accountsRepository.save(account);
    log.debug("Account created succesfully");
  }


}
