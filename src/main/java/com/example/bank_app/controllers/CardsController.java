package com.example.bank_app.controllers;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.dto.CardDto;
import com.example.bank_app.services.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardsController {

    private final CardsService cardsService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void  create(@RequestBody CardDto cardDto){
        cardsService.create(cardDto);
    }

}
