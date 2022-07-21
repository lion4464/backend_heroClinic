package com.example.demo.history_worker_salary;

import com.example.demo.exceptions.DataNotFoundException;

import java.util.List;
import java.util.UUID;

public interface HistoryWorkerSalaryService {
    List<HistoryWorkerSalaryDto> findByWorkerId(UUID id);
    HistoryWorkerSalary save(HistoryWorkerSalary obj);

    void saveAll(List<HistoryWorkerSalary> historyWorkerSalaryList);
}
