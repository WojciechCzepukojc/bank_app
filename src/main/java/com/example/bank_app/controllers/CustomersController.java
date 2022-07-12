package com.example.bank_app.controllers;

import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.services.CustomersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {

    private final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getPage(){
        return customersService.getPage();
    }

}
