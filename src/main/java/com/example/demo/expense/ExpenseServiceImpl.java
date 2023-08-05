package com.example.demo.expense;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.expense_template.ExpenseTemplateEntity;
import com.example.demo.expense_template.ExpenseTemplateService;
import com.example.demo.user.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseTemplateService expenseTemplateService;

    private static final Logger logger = LogManager.getLogger();

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseTemplateService expenseTemplateService) {
        this.expenseRepository = expenseRepository;
        this.expenseTemplateService = expenseTemplateService;
    }

    @Override
    public ExpenseEntity save(UserEntity user,ExpenseRequest request) throws DataNotFoundException {
        logger.info("Saving new expense {} to db",request.getSum());
        return getReadyEntity(user,request);
    }

    @Override
    public ExpenseEntity get(UUID id) throws DataNotFoundException {
        Optional<ExpenseEntity> res = expenseRepository.findById(id);
        if (res.isEmpty())
            throw new DataNotFoundException("expense is not found :(");
        return res.get();
    }

    @Override
    public String delete(UUID id) {
        expenseRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public List<ExpenseEntity> all(UserEntity user) {
        return expenseRepository.findAllByCompanyId(user.getCompanyId());
    }

    @Override
    public ExpenseEntity update(UserEntity user,ExpenseRequest obj) throws DataNotFoundException {
        logger.info("Updated Expense {} ",obj.getId());
          return getReadyEntity(user,obj);
    }

    private ExpenseEntity getReadyEntity(UserEntity user,ExpenseRequest obj) throws DataNotFoundException {
        ExpenseEntity expense=null;
        if (obj.getId()==null)
             expense = new ExpenseEntity();
        else{
            Optional<ExpenseEntity> entity= expenseRepository.findById(obj.getId());
            if (entity.isEmpty())
                throw new DataNotFoundException(obj.getId()+" isn't not found");
            expense = entity.get();
        }
       ExpenseTemplateEntity expenseTemplate = expenseTemplateService.get(obj.getExpenseTemplateId());
        expense.setExpenseTemplate(expenseTemplate);
        expense.setPaymentType(obj.getPaymentType());
        expense.setSum(obj.getSum());
        expense.setDescription(obj.getDescription());
        expense.setCompany(user.getCompany());
        return expenseRepository.save(expense);
    }
}
