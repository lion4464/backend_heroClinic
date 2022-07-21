package com.example.demo.expense_template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseTemplateDto {
    private UUID id;
    private String name;
    private Long createdDate;
}
