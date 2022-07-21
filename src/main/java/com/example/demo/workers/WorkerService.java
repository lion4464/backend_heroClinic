package com.example.demo.workers;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;

public interface WorkerService {
    WorkersEntity save(WorkerRequest request);
    WorkersEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<WorkersEntity> findAllInactiveStatus(UserEntity user);
    List<WorkersEntity> findAllActiveStatus(UserEntity user);
    WorkersEntity update(WorkerRequest obj);

}
