package com.example.demo.expense;

import com.example.demo.expense_template.ExpenseTemplateDto;
import com.example.demo.expense_template.ExpenseTemplateEntity;
import com.example.demo.generic.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {
    private UUID id;
    private String description;
    private Float sum;
    private PaymentType paymentType;
    private ExpenseTemplateDto expenseTemplate;
    private Long createdDate;
}
