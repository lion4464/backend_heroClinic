package com.example.demo.patient_dept;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.PaymentType;
import com.example.demo.patients.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class PatientDeptServiceImpl implements PatientDeptService {
    private final PatientDeptRepository patientDeptRepository;
    private final PatientService patientService;
    private static final Logger logger = LogManager.getLogger();

    public PatientDeptServiceImpl(PatientDeptRepository patientDeptRepository, PatientService patientService) {
        this.patientDeptRepository = patientDeptRepository;
        this.patientService = patientService;
    }

    public PatientDeptEntity save(PatientDeptRequest request){
        logger.info("Saving new  Patient Dept {} to db",request.getSum());
        PatientDeptEntity patientDeptEntity = new PatientDeptEntity();
        patientDeptEntity.setSum(request.getSum());
        patientDeptEntity.setPaymentType((request.getPaymentType()==null) ? PaymentType.CASH : request.getPaymentType());
        patientDeptEntity.setPatient(patientService.getId(request.getPatientId()));
        patientDeptRepository.save(patientDeptEntity);
        return patientDeptEntity;
    }
    public PatientDeptEntity get(UUID id) throws DataNotFoundException {
        Optional<PatientDeptEntity> res = patientDeptRepository.findById(id);
        if (res.isEmpty())
            throw new DataNotFoundException("Patient Dept is not found :(");
        return res.get();
    }
    public String delete(UUID id) {
        patientDeptRepository.deleteById(id);
        return "Successfully removed";
    }

    public List<PatientDeptEntity> all(){
        return patientDeptRepository.findAll();
    }

    @Override
    public PatientDeptEntity update(PatientDeptRequest obj){
        Optional<PatientDeptEntity> optional= patientDeptRepository.findById(obj.getId());
        if (optional.isEmpty())
            throw new DataNotFoundException(obj.getId()+" isn't not found in Patient Dept");
            PatientDeptEntity entity = optional.get();
            entity.setPaymentType(obj.getPaymentType());
            entity.setSum(obj.getSum());
        entity.setPatient(patientService.getId(obj.getPatientId()));
        return patientDeptRepository.save(entity);
    }

    @Override
    public Float summAllPaymentsByPatientAndCompanyId(UUID patientId, UUID companyId) {
        return patientDeptRepository.summAllPaymentsByPatient(patientId,companyId);
    }

    @Override
    public PatientDeptEntity save(PatientDeptEntity changed_patient_dept) {
        return patientDeptRepository.save(changed_patient_dept);
    }
}
