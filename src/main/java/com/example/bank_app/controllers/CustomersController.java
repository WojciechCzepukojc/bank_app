package com.example.bank_app.controllers;

import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.services.CustomersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getByIdPage(@PathVariable Long id){
        return customersService.getById(id);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void  create(@RequestBody CustomerDto customerDto){
        customersService.create(customerDto);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  delete(@PathVariable Long id){
        customersService.deleteById(id);
    }

}
