package com.example.bank_app.mappers;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountsMapper {

    @Mapping(target = "customerId", source = "customer.id")
    AccountDto map(Account account);

    @Mapping(target = "customer", ignore = true)
    Account map(AccountDto  accountDto);




}
