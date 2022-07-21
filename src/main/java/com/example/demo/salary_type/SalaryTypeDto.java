package com.example.demo.salary_type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SalaryTypeDto {
    private UUID id;
    private String name;
    private Long createdDate;
}
