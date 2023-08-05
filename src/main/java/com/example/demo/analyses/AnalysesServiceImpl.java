package com.example.demo.analyses;

import com.example.demo.exceptions.DataNotFoundException;

import com.example.demo.generic.GenericRepository;
import com.example.demo.generic.GenericServiceImpl;
import com.example.demo.generic.JpaGenericRepository;
import com.example.demo.generic.JpaGenericServiceImpl;
import com.example.demo.history_anaylse_amount.HistoryAnalyseService;
import com.example.demo.history_anaylse_amount.HistoryAnalysesEntity;
import com.example.demo.patients.PatientEntity;
import com.example.demo.user.UserEntity;
import com.example.demo.workers.WorkerService;
import com.example.demo.workers.WorkersEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalysesServiceImpl  extends JpaGenericServiceImpl<AnalysesEntity, UUID> implements AnalysesService{

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
    public AnalysesEntity saveAnalyse(UserEntity user,AnalysesRequest request) throws DataNotFoundException {
        WorkersEntity worker = workerService.findById(request.getWorkerId());
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
    public AnalysesEntity updateAnalayse(AnalysesRequest obj) throws DataNotFoundException {
        logger.info("Updated Analyse {} ",obj.getName());
        Optional<AnalysesEntity> entity = analysesRepository.findById(obj.getId());
        if (entity.isEmpty())
            throw new DataNotFoundException(obj.getId()+"Analyse isn't not found");
        WorkersEntity worker = workerService.findById(obj.getWorkerId());
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
    public List<AnalysesEntity> updateAll(List<AnalysesRequest> objList) throws DataNotFoundException {
       List<HistoryAnalysesEntity> historyAnalyseList = new ArrayList<>();

        List<UUID> analyseIds=objList.stream().map(AnalysesRequest::getId).collect(Collectors.toList());
        List<AnalysesEntity> analysesEntityList =analysesRepository.findAllById(analyseIds);
        List<AnalysesEntity> changedAnalyseList = new ArrayList<>();
        int i=0;
            for (AnalysesRequest entity:objList) {
                if (!entity.getPayAmount().equals(analysesEntityList.get(i).getPayAmount()) ){
                    WorkersEntity worker = workerService.findById(entity.getWorkerId());
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

    @Override
    public List<AnalysesEntity> getAll(UserEntity user) {
        return analysesRepository.findAllByCompanyId(user.getCompanyId());
    }

    public Long getMillisecondNow(){
        Date date = new Date();
        return date.getTime();
    }


    @Override
    protected JpaGenericRepository<AnalysesEntity, UUID> getRepository() {
        return analysesRepository;
    }
}
