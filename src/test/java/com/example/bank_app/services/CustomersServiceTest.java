package com.example.bank_app.services;

import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.mappers.CustomersMapper;
import com.example.bank_app.models.Customer;
import com.example.bank_app.repositories.CustomersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;


@SpringBootTest
class CustomersServiceTest {

    @MockBean
    CustomersRepository customersRepository;

    @SpyBean
    CustomersMapper customersMapper;

    @Autowired
    private CustomersService customersService;

    @BeforeEach
    void reset() {
        Mockito.reset(customersRepository);
    }

    @Test
    void testCreateSucces() {
        //given
        CustomerDto customerDto = CustomerDto.builder()
                .name("Maja")
                .surname("Pszczółka")
                .pesel("85230585745")
                .build();
        //when
        customersService.create(customerDto);
        //then
        Mockito.verify(customersMapper).map(customerDto);
        Mockito.verify(customersRepository).save(Mockito.any(Customer.class));
        Mockito.verifyNoMoreInteractions(customersMapper, customersRepository);


    }
}