package com.example.demo.history_anaylse_amount;

import com.example.demo.company.CompanyEntity;
import com.example.demo.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HistoryAnalyseServiceImpl implements HistoryAnalyseService {
   private final HistoryAnalyseRepository historyAnalyseRepository;

    public HistoryAnalyseServiceImpl(HistoryAnalyseRepository historyAnalyseRepository) {
        this.historyAnalyseRepository = historyAnalyseRepository;
    }

    @Override
    public HistoryAnalysesEntity findByWorkerId(UUID id) {
        return historyAnalyseRepository.getById(id);
    }

    @Override
    public HistoryAnalysesEntity save(CompanyEntity company, HistoryAnalysesEntity obj) {
        obj.setCompany(company);
        return historyAnalyseRepository.save(obj);
    }

    @Override
    public void saveAll(List<HistoryAnalysesEntity> historyAnalyseList) {
         historyAnalyseRepository.saveAll(historyAnalyseList);
    }
}
