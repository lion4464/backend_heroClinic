package com.example.demo.patients;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Patientmapper {

    PatientDTO toDto(PatientEntity patientEntity);
    PatientEntity fromDto(PatientDTO patientDTO);
    List<PatientDTO> fromPageEntity(Page<PatientEntity> page);

}
