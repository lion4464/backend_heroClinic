package com.example.demo.expense_template;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;


public interface ExpenseTemplateService {
    ExpenseTemplateEntity saveRole(UserEntity user, ExpenseTemplateRequest request);
    ExpenseTemplateEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<ExpenseTemplateEntity> all(UserEntity user);
    ExpenseTemplateEntity update(ExpenseTemplateRequest obj);

}
