package com.example.demo.cashier;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Cashiermapper {
    CashierDto toDto(CashierEntity cashierEntity);
    CashierForRoomInvoice toForRoomInvoice(CashierEntity cashierEntity);
    CashierEntity fromDto(CashierRequest cashierRequest);
    List<CashierDto> fromPageEntity(List<CashierEntity>  entityList);
}
