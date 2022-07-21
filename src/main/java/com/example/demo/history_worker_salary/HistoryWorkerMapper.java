package com.example.demo.history_worker_salary;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryWorkerMapper {
    List<HistoryWorkerSalaryDto> fromPageEntity(List<HistoryWorkerSalary> entityList);
}
