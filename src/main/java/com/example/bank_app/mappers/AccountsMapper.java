package com.example.bank_app.mappers;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.models.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountsMapper {

    AccountDto map(Account account);

    Account map(AccountDto  accountDto);


}
