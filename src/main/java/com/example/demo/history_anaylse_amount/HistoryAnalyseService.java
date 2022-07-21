package com.example.demo.history_anaylse_amount;

import com.example.demo.company.CompanyEntity;
import com.example.demo.company.CompanyService;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;

public interface HistoryAnalyseService {
    HistoryAnalysesEntity findByWorkerId(UUID id);
    HistoryAnalysesEntity save(CompanyEntity company, HistoryAnalysesEntity obj);

    void saveAll(List<HistoryAnalysesEntity> historyAnalyseList);
}
