package com.example.bank_app.services;

import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.exceptions.ResourceNotFoundException;
import com.example.bank_app.exceptions.ResurceValidationException;
import com.example.bank_app.mappers.CustomersMapper;
import com.example.bank_app.models.Customer;
import com.example.bank_app.repositories.CustomersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomersService {

    private final CustomersRepository customersRepository;

    private final CustomersMapper customersMapper;


  public CustomersService(CustomersRepository customersRepository, CustomersMapper customersMapper) {
    this.customersRepository = customersRepository;
    this.customersMapper = customersMapper;
  }

  public void create (CustomerDto customerDto){
    log.info("Creating customer '{}'", customerDto);
    Customer customer = customersMapper.map(customerDto);
    customersRepository.save(customer);
    log.debug("Customer created succesfully");
  }

    public CustomerDto getById (Long id){
      log.info("Fetching customer with id '{}'", id);
      Customer customer = customersRepository.findById(id).orElseThrow(
              () -> getCustomerNotFoundException(id));
      return customersMapper.map(customer);
    }



  public List<CustomerDto> getPage () {
    log.info("Fetching customers page");
      return customersRepository.findAll()
              .stream()
              .map(customer -> customersMapper.map(customer))
              .collect(Collectors.toList());

    }

    public void updateById(Long id, CustomerDto customerDto){
      log.info("Updating customer with id '{}'. New customer: '{}'", id, customerDto);
      if (!customersRepository.existsById(id)) {
        log.warn("Customer with id '{}' not found", id);
        throw getCustomerNotFoundException(id);
      }

      Long customerDtoId = customerDto.getId();
      if (!id.equals(customerDtoId)) {
        log.warn("Id parameter '{}' and customer id '{}' does not match", id, customerDtoId);
        throw new ResurceValidationException(
                String.format("Id parameter '%s' and customer id '%s' does not match",
                        id, customerDtoId)
        );
      }

      Customer customer = customersMapper.map(customerDto);
      customersRepository.save(customer);
    }

    public void deleteById (Long id){
      log.info("Removing customer with id '{}'. New customer: '{}'", id);
      customersRepository.deleteById(id);
    }

  private ResourceNotFoundException getCustomerNotFoundException(Long id) {
    return new ResourceNotFoundException(String.format("Customer with id '%s' not found", id));
  }
}
