package com.example.bank_app.services;

import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.mappers.CustomersMapper;
import com.example.bank_app.models.Customer;
import com.example.bank_app.repositories.CustomersRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;

    private final CustomersMapper customersMapper;

  public CustomersService(CustomersRepository customersRepository, CustomersMapper customersMapper) {
    this.customersRepository = customersRepository;
    this.customersMapper = customersMapper;
  }

  public void create (CustomerDto customerDto){
    Customer customer = customersMapper.map(customerDto);
    customersRepository.save(customer);
  }

    public CustomerDto getById (Long id){
      Customer customer = customersRepository.getReferenceById(id);
      return customersMapper.map(customer);
    }

    public List<CustomerDto> getPage () {
      return customersRepository.findAll()
              .stream()
              .map(customer -> customersMapper.map(customer))
              .collect(Collectors.toList());

    }

    public void update (){

    }

    public void deleteById (Long id){
      customersRepository.deleteById(id);
    }
}
