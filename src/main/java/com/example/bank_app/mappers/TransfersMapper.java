package com.example.bank_app.mappers;

import com.example.bank_app.dto.TransferDto;
import com.example.bank_app.models.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransfersMapper {

    @Mapping(target = "accountId", source = "account.id")
    TransferDto map(Transfer transfer);

    @Mapping(target = "account", ignore = true)
    Transfer map(TransferDto transferDto);


}
