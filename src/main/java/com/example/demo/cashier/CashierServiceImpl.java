package com.example.demo.cashier;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.PaymentType;
import com.example.demo.patient_dept.PatientDeptService;
import com.example.demo.patients.PatientEntity;
import com.example.demo.patients.PatientService;
import com.example.demo.user.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CashierServiceImpl implements CashierService {
    private final CashierRepository cashierRepository;
    private final PatientService patientService;
    private final PatientDeptService patientDeptService;
    private static final Logger logger = LogManager.getLogger();

    public CashierServiceImpl(CashierRepository cashierRepository, PatientService patientService, PatientDeptService patientDeptService) {
        this.cashierRepository = cashierRepository;
        this.patientService = patientService;
        this.patientDeptService = patientDeptService;
    }

    public CashierEntity save(CashierRequest request) {
        logger.info("Saving new Cashier {} to db", request.getSum());
        PatientEntity patient = patientService.getId(request.getPatientId());
        CashierEntity cashierEntity = new CashierEntity();
        cashierEntity.setSum(request.getSum());
        cashierEntity.setPaymentType((request.getPaymentType() == null) ? PaymentType.CASH : request.getPaymentType());
        cashierEntity.setPatient(patient);
        cashierEntity.setCompany(patient.getCompany());
        cashierRepository.save(cashierEntity);

        return cashierEntity;
    }

    public CashierEntity get(UUID id) throws DataNotFoundException {
        Optional<CashierEntity> res = cashierRepository.findById(id);
        if (res.isEmpty())
            throw new DataNotFoundException("Casheir is not found :(");
        return res.get();
    }

    public String delete(UUID id) {
        cashierRepository.deleteById(id);
        return "Successfully removed";
    }

    public List<CashierEntity> all(UserEntity user) {
        return cashierRepository.findAllByCompanyId(user.getCompanyId());
    }

    @Override
    public CashierEntity update(CashierRequest obj) {
        Optional<CashierEntity> optional = cashierRepository.findById(obj.getId());
        if (optional.isEmpty())
            throw new DataNotFoundException(obj.getId() + " isn't not found in Casheir");
        PatientEntity patient = patientService.getId(obj.getPatientId());
        CashierEntity entity = optional.get();
        entity.setPaymentType(obj.getPaymentType());
        entity.setSum(obj.getSum());
        entity.setPatient(patient);
        entity.setCompany(patient.getCompany());
        return cashierRepository.save(entity);
    }

    @Override
    public Float summAllPaymentsByPatient(UserEntity user,UUID id) {
        return cashierRepository.summAllPaymentsByPatientAndCompanyId(id,user.getCompanyId());
    }

    @Override
    public HashMap<String, Float> getPatientDept(UserEntity user,UUID patientid) {
        HashMap<String, Float> res = new HashMap<>();
        Float payments = (cashierRepository.summAllPaymentsByPatientAndCompanyId(patientid,user.getCompanyId()) == null) ? 0 : cashierRepository.summAllPaymentsByPatientAndCompanyId(patientid,user.getCompanyId());
        Float depts = (patientDeptService.summAllPaymentsByPatientAndCompanyId(patientid,user.getCompanyId()) == null) ? 0 : patientDeptService.summAllPaymentsByPatientAndCompanyId(patientid,user.getCompanyId());
        res.put("payments", payments);
        res.put("depts", depts);
        return res;
    }
}
