package com.example.demo.patient_dept;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientDeptmapper {
    PatientDeptDto toDto(PatientDeptEntity patientDeptEntity);
    PatientDeptEntity fromDto(PatientDeptRequest patientDeptRequest);
    List<PatientDeptDto> fromPageEntity(List<PatientDeptEntity>  entityList);
}
