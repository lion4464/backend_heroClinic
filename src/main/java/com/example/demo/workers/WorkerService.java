package com.example.demo.workers;

import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.JpaGenericService;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;

public interface WorkerService extends JpaGenericService<WorkersEntity, UUID> {
    WorkersEntity saveWorker(WorkerRequest request);
    List<WorkersEntity> findAllInactiveStatus(UserEntity user);
    List<WorkersEntity> findAllActiveStatus(UserEntity user);
    WorkersEntity updateWorker(WorkerRequest obj) throws DataNotFoundException;

}
