package com.example.demo.expense_template;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseTemplatemapper {
    ExpenseTemplateDto toDto(ExpenseTemplateEntity entity);
    ExpenseTemplateEntity fromDto(ExpenseTemplateRequest request);
    List<ExpenseTemplateDto> fromPageEntity(List<ExpenseTemplateEntity> entityList);
}

