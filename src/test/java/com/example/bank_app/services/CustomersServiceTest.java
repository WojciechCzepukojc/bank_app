package com.example.bank_app.services;

import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.exceptions.ResourceNotFoundException;
import com.example.bank_app.exceptions.ResurceValidationException;
import com.example.bank_app.mappers.CustomersMapper;
import com.example.bank_app.models.Customer;
import com.example.bank_app.repositories.CustomersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;


@SpringBootTest
class CustomersServiceTest {

    @MockBean
    private CustomersRepository customersRepository;

    @SpyBean
    private CustomersMapper customersMapper;

    @Autowired
    private CustomersService customersService;

    private CustomerDto customerDto;

    @BeforeEach
    void reset() {
        Mockito.reset(customersRepository);

        customerDto = CustomerDto.builder()
                .id(1L)
                .name("Maja")
                .surname("Pszczółka")
                .pesel("90083004219")
                .build();

    }

    @Test
    void testCreateSucces() {
        //given
        //when
        customersService.create(customerDto);
        //then
        Mockito.verify(customersMapper).map(customerDto);
        Mockito.verify(customersRepository).save(Mockito.any(Customer.class));
        Mockito.verifyNoMoreInteractions(customersMapper, customersRepository);
    }

    @Test
    void testUpdateHappyPath() {
        //given
        final Long id = customerDto.getId();
        final Customer expectedCustomer = customersMapper.map(customerDto);

        Mockito.when(customersRepository.existsById(id)).thenReturn(true);

        //when
        customersService.updateById(id, customerDto);

        //then
        Mockito.verify(customersRepository).existsById(id);
        Mockito.verify(customersMapper, times(2)).map(customerDto);
        Mockito.verify(customersRepository).save(Mockito.any(Customer.class));
        Mockito.verifyNoMoreInteractions(customersMapper, customersRepository);
    }

    @Test
    void testUpdateCustomerNotFound() {
        //given
        final Long id = customerDto.getId();

        Mockito.when(customersRepository.existsById(id)).thenReturn(false);

        //when
        Assertions.assertThrows(ResourceNotFoundException.class, ()->customersService.updateById(id, customerDto));

        //then
        Mockito.verify(customersRepository).existsById(id);
        Mockito.verifyNoMoreInteractions(customersMapper, customersRepository);
        Mockito.verifyNoInteractions(customersMapper);
    }

    @Test
    void testUpdateIdsConflict() {
        //given
        final Long id = 2L;

        Mockito.when(customersRepository.existsById(id)).thenReturn(true);

        //when
        Assertions.assertThrows(ResurceValidationException.class, ()->customersService.updateById(id, customerDto));

        //then
        Mockito.verify(customersRepository).existsById(id);
        Mockito.verifyNoMoreInteractions(customersMapper, customersRepository);
        Mockito.verifyNoInteractions(customersMapper);
    }

    @Test
    void testGetCustomerSucces() {
        //given
        //when
        Optional<Customer> optionalCustomer = customersRepository.findById(customerDto.getId());
        //then
        Mockito.verify(customersRepository).findById(customerDto.getId());
        Mockito.doReturn(Optional.of(optionalCustomer)).when(customersRepository).findById(1L);
    }
}