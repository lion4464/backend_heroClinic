package com.example.demo.history_worker_salary;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.JpaGenericService;
import com.example.demo.workers.WorkersEntity;

import java.util.List;
import java.util.UUID;

public interface HistoryWorkerSalaryService  extends JpaGenericService<HistoryWorkerSalary, UUID> {
    List<HistoryWorkerSalaryDto> findByWorkerId(UUID id);
    HistoryWorkerSalary save(HistoryWorkerSalary obj);

    void saveAll(List<HistoryWorkerSalary> historyWorkerSalaryList);
}
