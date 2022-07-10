package com.example.bank_app.services;

import com.example.bank_app.models.Customer;
import com.example.bank_app.repositories.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomersService {

    private final CustomersRepository customersRepository;



  public void create (Customer customer){

    customersRepository.save(customer);
  }

    public void getById (){

    }

    public void getPage (){

    }

    public void update (){

    }

    public void delete (){

    }
}
