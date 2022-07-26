package com.example.bank_app.repositories;

import com.example.bank_app.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomersRepositoryTest{

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
        //given
        Customer customer = Customer.builder()
                .name("Jurek")
                .surname("Kiler")
                .pesel("88062845876")
                .build();

        Customer expectedCustomer = customersRepository.save(customer);

        //when
        List<Customer> result = customersRepository.findAll();

        //then
        assertNotNull(result);
        assertEquals(7, result.size());



    }
}