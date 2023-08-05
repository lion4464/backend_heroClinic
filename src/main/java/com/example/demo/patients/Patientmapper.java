package com.example.demo.patients;

import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.analyses.AnalysesResponse;
import com.example.demo.generic.GenericAuditMapper;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Patientmapper extends GenericAuditMapper<PatientEntity,PatientDTO> {
    PatientEntity fromDto(PatientDTO patientDTO);
}
