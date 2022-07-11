package com.example.bank_app.mappers;

import com.example.bank_app.dto.CardDto;
import com.example.bank_app.models.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardsMapper {

    CardDto map(Card card);

    Card map(CardDto cardDto);


}
