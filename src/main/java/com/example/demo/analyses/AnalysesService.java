package com.example.demo.analyses;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.JpaGenericService;
import com.example.demo.patients.PatientEntity;
import com.example.demo.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface AnalysesService  extends JpaGenericService<AnalysesEntity, UUID> {
    AnalysesEntity saveAnalyse(UserEntity user, AnalysesRequest request) throws DataNotFoundException;
    AnalysesEntity updateAnalayse(AnalysesRequest obj) throws DataNotFoundException;
    List<AnalysesEntity> updateAll(List<AnalysesRequest> objList) throws DataNotFoundException;

    List<AnalysesEntity> getAll(UserEntity userEntity);
}
