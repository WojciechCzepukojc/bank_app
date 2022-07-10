package com.example.bank_app.mappers;

import com.example.bank_app.dto.CardDto;
import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.models.Card;
import com.example.bank_app.models.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto map(Customer customer);

    Customer map(CustomerDto customerDto);


}
