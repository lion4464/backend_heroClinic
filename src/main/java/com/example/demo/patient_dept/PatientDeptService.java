package com.example.demo.patient_dept;

import com.example.demo.exceptions.DataNotFoundException;

import java.util.List;
import java.util.UUID;

public interface PatientDeptService {
    PatientDeptEntity save(PatientDeptRequest request) throws DataNotFoundException;
    PatientDeptEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<PatientDeptEntity> all();
    PatientDeptEntity update(PatientDeptRequest obj) throws DataNotFoundException;
    Float summAllPaymentsByPatientAndCompanyId(UUID patientid, UUID companyId);
    PatientDeptEntity save(PatientDeptEntity changed_patient_dept);

}
