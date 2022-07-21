package com.example.demo.worker_expense;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkerExpensemapper {
    WorkerExpenseDto toDto(WorkerExpenseEntity entity);
    WorkerExpenseEntity fromDto(WorkerExpenseRequest request);
    List<WorkerExpenseDto> fromPageEntity(List<WorkerExpenseEntity> entityList);
}

