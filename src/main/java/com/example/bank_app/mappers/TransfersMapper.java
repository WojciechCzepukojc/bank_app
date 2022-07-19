package com.example.bank_app.mappers;

import com.example.bank_app.dto.TransferDto;
import com.example.bank_app.models.Transfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransfersMapper {

    TransferDto map(Transfer transfer);

    Transfer map(TransferDto transferDto);


}
