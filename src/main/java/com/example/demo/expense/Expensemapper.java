package com.example.demo.expense;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Expensemapper {
    ExpenseDto toDto(ExpenseEntity entity);
    ExpenseEntity fromDto(ExpenseRequest request);
    List<ExpenseDto> fromPageEntity(List<ExpenseEntity> entityList);
}

