package com.example.bank_app.repositories;

import com.example.bank_app.models.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomersRepositoryTest {

    @Autowired
    private CustomersRepository customersRepository;

    @Test
    void testSaveNewCustomerSucces() {
        //given
        Customer expectedCustomer = Customer.builder()
                .name("Jurek")
                .surname("Kiler")
                .pesel("88062845876")
                .build();
        //when
        Customer actualCustomer = customersRepository.save(expectedCustomer);
        //then
                assertNotNull(actualCustomer.getId());
                assertEquals(expectedCustomer, actualCustomer);
                assertEquals(expectedCustomer.getName(), actualCustomer.getName());
                assertEquals(expectedCustomer.getSurname(), actualCustomer.getSurname());
                assertEquals(expectedCustomer.getPesel(), actualCustomer.getPesel());

    }

    @Test
    void testGetCustomerListSucces() {

    }
}