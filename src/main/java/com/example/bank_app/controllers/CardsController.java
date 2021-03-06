package com.example.bank_app.controllers;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.dto.CardDto;
import com.example.bank_app.services.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CardDto> getByAccountId(@PathVariable Long id){
        return cardsService.getByAccountId(id);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void  update(@PathVariable Long id,
                        @RequestBody CardDto cardDto){
        cardsService.updateById(id, cardDto);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  delete(@PathVariable Long id){
        cardsService.deleteById(id);
    }

}
