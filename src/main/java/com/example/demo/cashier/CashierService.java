package com.example.demo.cashier;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface CashierService {
    CashierEntity save(CashierRequest request);
    CashierEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<CashierEntity> all(UserEntity user);
    CashierEntity update(CashierRequest obj);
    Float summAllPaymentsByPatient(UserEntity user, UUID id);

    HashMap<String,Float> getPatientDept(UserEntity user,UUID patientid);
}
