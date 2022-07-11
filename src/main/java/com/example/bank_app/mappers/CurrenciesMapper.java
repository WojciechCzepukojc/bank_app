package com.example.bank_app.mappers;

import com.example.bank_app.dto.CurrencyDto;
import com.example.bank_app.models.Currency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrenciesMapper {

    CurrencyDto map(Currency currency);

    Currency map(CurrencyDto currencyDto);


}
