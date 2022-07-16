package com.example.bank_app.controllers;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.dto.CustomerDto;
import com.example.bank_app.services.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void  create(@RequestBody AccountDto accountDto){
        accountsService.create(accountDto);
    }
    @GetMapping("customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> getByCustomerId(@PathVariable Long id){
        return accountsService.getByCustomerId(id);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  delete(@PathVariable Long id){
        accountsService.deleteById(id);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void  update(@PathVariable Long id,
                        @RequestBody AccountDto accountDto){
        accountsService.updateById(id, accountDto);
    }






}
