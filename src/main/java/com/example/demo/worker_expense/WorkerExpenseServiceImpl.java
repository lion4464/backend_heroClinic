package com.example.demo.worker_expense;

import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.expense_template.ExpenseTemplateEntity;
import com.example.demo.expense_template.ExpenseTemplateService;
import com.example.demo.user.UserEntity;
import com.example.demo.workers.WorkerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkerExpenseServiceImpl implements WorkerExpenseService {

    private final WorkerExpenseRepository workerExpenseRepository;
    private final ExpenseTemplateService expenseTemplateService;
    private final WorkerService workerService;

    private static final Logger logger = LogManager.getLogger();

    public WorkerExpenseServiceImpl(WorkerExpenseRepository workerExpenseRepository, ExpenseTemplateService expenseTemplateService, WorkerService workerService) {
        this.workerExpenseRepository = workerExpenseRepository;
        this.expenseTemplateService = expenseTemplateService;
        this.workerService = workerService;
    }

    @Override
    public WorkerExpenseEntity save(WorkerExpenseRequest request, UserEntity user) {
        logger.info("Saving new expense {} to db",request.getSum());
        return getReadyEntity(request,user);
    }

    @Override
    public WorkerExpenseEntity get(UUID id) throws DataNotFoundException {
        Optional<WorkerExpenseEntity> res = workerExpenseRepository.findById(id);
        if (res.isEmpty())
            throw new DataNotFoundException("expense is not found :(");
        return res.get();
    }

    @Override
    public String delete(UUID id) {
        workerExpenseRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public List<WorkerExpenseEntity> all(UserEntity user) {
        return workerExpenseRepository.findAllByCompanyId(user.getCompanyId());
    }

    @Override
    public WorkerExpenseEntity update(WorkerExpenseRequest obj) {
        logger.info("Updated Expense {} ",obj.getId());
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          return getReadyEntity(obj,userDetails.getUserEntity());
    }

    private WorkerExpenseEntity getReadyEntity(WorkerExpenseRequest obj,UserEntity user){
        WorkerExpenseEntity expense=null;
        if (obj.getId()==null)
             expense = new WorkerExpenseEntity();
        else{
            Optional<WorkerExpenseEntity> entity= workerExpenseRepository.findById(obj.getId());
            if (entity.isEmpty())
                throw new DataNotFoundException(obj.getId()+" isn't not found");
            expense = entity.get();
        }
       ExpenseTemplateEntity expenseTemplate = expenseTemplateService.get(obj.getExpenseTemplateId());
        expense.setExpenseTemplate(expenseTemplate);
        expense.setPaymentType(obj.getPaymentType());
        expense.setSum(obj.getSum());
        expense.setCompany(user.getCompany());
        expense.setDescription(obj.getDescription());
        expense.setWorker(workerService.get(obj.getWokerId()));
        return workerExpenseRepository.save(expense);
    }
}
