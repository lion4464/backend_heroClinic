package com.example.demo.expense_template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ExpenseTemplateRepository extends JpaRepository<ExpenseTemplateEntity, UUID> {
    List<ExpenseTemplateEntity> findAllByCompanyId(UUID companyId);
}
