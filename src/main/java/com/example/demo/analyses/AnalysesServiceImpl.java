package com.example.demo.analyses;

import com.example.demo.exceptions.DataNotFoundException;

import com.example.demo.history_anaylse_amount.HistoryAnalyseService;
import com.example.demo.history_anaylse_amount.HistoryAnalysesEntity;
import com.example.demo.user.UserEntity;
import com.example.demo.workers.WorkerService;
import com.example.demo.workers.WorkersEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalysesServiceImpl implements AnalysesService{

    private final AnalysesRepository analysesRepository;
    private final HistoryAnalyseService historyAnalyseServiceService;
    private final WorkerService workerService;
    private static final Logger logger = LogManager.getLogger();

    public AnalysesServiceImpl(AnalysesRepository analysesRepository, HistoryAnalyseService historyAnalyseServiceService, WorkerService workerService) {
        this.analysesRepository = analysesRepository;
        this.historyAnalyseServiceService = historyAnalyseServiceService;
        this.workerService = workerService;
    }


    @Override
    public AnalysesEntity save(UserEntity user,AnalysesRequest request) {
        WorkersEntity worker = workerService.get(request.getWorkerId());
        AnalysesEntity entity = new AnalysesEntity();
        entity.setName(request.getName());
        entity.setPayAmount(request.getPayAmount());
        logger.info("Saving new analyses {} to db",request.getName());
        entity.setWorker(worker);
        entity.setCompany(user.getCompany());
        analysesRepository.save(entity);
            HistoryAnalysesEntity historyAnalyses = new HistoryAnalysesEntity();
            historyAnalyses.setAnalyse(entity);
            historyAnalyses.setAmount(request.getPayAmount());
            historyAnalyseServiceService.save(user.getCompany(),historyAnalyses);
        return entity;
    }

    @Override
    public AnalysesEntity get(UUID id) throws DataNotFoundException {
        Optional<AnalysesEntity> res = analysesRepository.findById(id);
        if (res.isEmpty())
            throw new DataNotFoundException("Analyse is not found :(");
        return res.get();
    }

    @Override
    public String delete(UUID id) {
    analysesRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public List<AnalysesEntity> all(UserEntity user) {
       return analysesRepository.findAllByCompanyId(user.getCompanyId());
    }

    @Override
    public AnalysesEntity update(AnalysesRequest obj) {
        logger.info("Updated Analyse {} ",obj.getName());
        Optional<AnalysesEntity> entity = analysesRepository.findById(obj.getId());
        if (entity.isEmpty())
            throw new DataNotFoundException(obj.getId()+"Analyse isn't not found");
        WorkersEntity worker = workerService.get(obj.getWorkerId());
        AnalysesEntity realEntity = entity.get();
        realEntity.setName(obj.getName());
        realEntity.setModifiedDate(getMillisecondNow());
        realEntity.setWorker(worker);
        if (!realEntity.getPayAmount().equals(obj.getPayAmount())) {
            realEntity.setPayAmount(obj.getPayAmount());
            HistoryAnalysesEntity historyAnalyse = new HistoryAnalysesEntity();
            historyAnalyse.setAnalyse(realEntity);
            historyAnalyse.setAmount(obj.getPayAmount());
            historyAnalyseServiceService.save(realEntity.getCompany(),historyAnalyse);
        }
        return analysesRepository.save(realEntity);
    }

    @Override
    public List<AnalysesEntity> updateAll(List<AnalysesRequest> objList) {
       List<HistoryAnalysesEntity> historyAnalyseList = new ArrayList<>();

        List<UUID> analyseIds=objList.stream().map(AnalysesRequest::getId).collect(Collectors.toList());
        List<AnalysesEntity> analysesEntityList =analysesRepository.findAllById(analyseIds);
        List<AnalysesEntity> changedAnalyseList = new ArrayList<>();
        int i=0;
            for (AnalysesRequest entity:objList) {
                if (!entity.getPayAmount().equals(analysesEntityList.get(i).getPayAmount()) ){
                    WorkersEntity worker = workerService.get(entity.getWorkerId());
                HistoryAnalysesEntity historyAnalysesEntity = new HistoryAnalysesEntity();
                historyAnalysesEntity.setAmount(entity.getPayAmount());
                historyAnalysesEntity.setAnalyse(analysesEntityList.get(i));
                historyAnalysesEntity.setModifiedDate(getMillisecondNow());
                    analysesEntityList.get(i).setModifiedDate(getMillisecondNow());
                    analysesEntityList.get(i).setPayAmount(entity.getPayAmount());
                    analysesEntityList.get(i).setName(entity.getName());
                    analysesEntityList.get(i).setWorker(worker);

                historyAnalyseList.add(historyAnalysesEntity);
                changedAnalyseList.add(analysesEntityList.get(i));
                }
                i++;
            }
        historyAnalyseServiceService.saveAll(historyAnalyseList);

        analysesRepository.saveAll(changedAnalyseList);
        return changedAnalyseList;
    }

    public Long getMillisecondNow(){
        Date date = new Date();
        return date.getTime();
    }
}
