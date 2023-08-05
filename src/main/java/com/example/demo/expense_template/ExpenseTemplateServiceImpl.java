package com.example.demo.expense_template;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseTemplateServiceImpl implements ExpenseTemplateService {

    private final ExpenseTemplateRepository expenseTemplateRepository;


    private static final Logger logger = LogManager.getLogger();

    public ExpenseTemplateServiceImpl(ExpenseTemplateRepository expenseTemplateRepository) {
        this.expenseTemplateRepository = expenseTemplateRepository;
    }

    @Override
    public ExpenseTemplateEntity saveRole(UserEntity user,ExpenseTemplateRequest request) {
        logger.info("Saving new Updated Expense Template {} to db",request.getName());
        ExpenseTemplateEntity expenseTemplate = new ExpenseTemplateEntity();
        expenseTemplate.setName(request.getName());
        expenseTemplate.setCompany(user.getCompany());
        return expenseTemplateRepository.save(expenseTemplate);
    }

    @Override
    public ExpenseTemplateEntity get(UUID id) throws DataNotFoundException {
        Optional<ExpenseTemplateEntity> res = expenseTemplateRepository.findById(id);
        if (res.isEmpty())
            throw new DataNotFoundException("Updated Expense Template is not found :(");
        return res.get();
    }

    @Override
    public String delete(UUID id) {
        expenseTemplateRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public List<ExpenseTemplateEntity> all(UserEntity user) {
        return expenseTemplateRepository.findAllByCompanyId(user.getCompanyId());
    }

    @Override
    public ExpenseTemplateEntity update(ExpenseTemplateRequest obj) throws DataNotFoundException {
        logger.info("Updated Expense Template {} ",obj.getName());
        Optional<ExpenseTemplateEntity> entity= expenseTemplateRepository.findById(obj.getId());
        if (entity.isEmpty())
            throw new DataNotFoundException(obj.getId()+" isn't not found");
          ExpenseTemplateEntity ExpenseTemplateEntity = entity.get();
          ExpenseTemplateEntity.setName(obj.getName());
        return expenseTemplateRepository.save(ExpenseTemplateEntity);
    }
}
