package com.example.demo.worker_expense;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;


public interface WorkerExpenseService {
    WorkerExpenseEntity save(WorkerExpenseRequest request, UserEntity user);
    WorkerExpenseEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<WorkerExpenseEntity> all(UserEntity user);
    WorkerExpenseEntity update(WorkerExpenseRequest obj);

}
