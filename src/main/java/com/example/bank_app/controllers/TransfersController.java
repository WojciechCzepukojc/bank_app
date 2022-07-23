package com.example.bank_app.controllers;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.dto.TransferDto;
import com.example.bank_app.services.TransfersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransfersController {

    private final TransfersService transfersService;


    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TransferDto transferDto, Long id) {
        transfersService.create(transferDto, id);
    }
}
