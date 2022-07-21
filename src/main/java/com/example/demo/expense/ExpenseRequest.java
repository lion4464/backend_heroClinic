package com.example.demo.expense;

import com.example.demo.expense_template.ExpenseTemplateDto;
import com.example.demo.generic.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {
    private UUID id;
    private String description;

    @NotNull(message = "sum is not be null")
    @Min(0)
    private Float sum;

    private PaymentType paymentType;

    @NotNull(message = "expenceTemplateId is not be null")
    private UUID expenseTemplateId;;
}
