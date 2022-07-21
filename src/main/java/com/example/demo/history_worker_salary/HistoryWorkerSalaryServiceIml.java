package com.example.demo.history_worker_salary;

import com.example.demo.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class HistoryWorkerSalaryServiceIml implements HistoryWorkerSalaryService{
    private final HistoryWorkerSalaryRepository historyWorkerSalaryRepository;
    private final HistoryWorkerMapper historyWorkerMapper;

    public HistoryWorkerSalaryServiceIml(HistoryWorkerSalaryRepository historyWorkerSalaryRepository, HistoryWorkerMapper historyWorkerMapper) {
        this.historyWorkerSalaryRepository = historyWorkerSalaryRepository;
        this.historyWorkerMapper = historyWorkerMapper;
    }

    @Override
    public List<HistoryWorkerSalaryDto> findByWorkerId(UUID id) throws DataNotFoundException {
        return historyWorkerMapper.fromPageEntity(historyWorkerSalaryRepository.findByWorkerId(id));
    }

    @Override
    public HistoryWorkerSalary save(HistoryWorkerSalary obj) {
     return historyWorkerSalaryRepository.save(obj);
    }

    @Override
    public void saveAll(List<HistoryWorkerSalary> historyWorkerSalaryList) {
        historyWorkerSalaryRepository.saveAll(historyWorkerSalaryList);
    }
}
