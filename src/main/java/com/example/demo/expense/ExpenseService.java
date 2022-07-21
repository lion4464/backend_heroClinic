package com.example.demo.expense;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;


public interface ExpenseService {
    ExpenseEntity save(UserEntity user,ExpenseRequest request);
    ExpenseEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<ExpenseEntity> all(UserEntity user);
    ExpenseEntity update(UserEntity user,ExpenseRequest obj);

}
