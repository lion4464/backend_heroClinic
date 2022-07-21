package com.example.demo.analyses;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;

public interface AnalysesService {
    AnalysesEntity save(UserEntity user, AnalysesRequest request);
    AnalysesEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<AnalysesEntity> all(UserEntity user);
    AnalysesEntity update(AnalysesRequest obj);
    List<AnalysesEntity> updateAll(List<AnalysesRequest> objList);
}
