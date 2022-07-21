package com.example.demo.salary_type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SalaryTypeRequest {
    private UUID id;
    @NotBlank(message = "Name must be filled because it is reuired!!!")
    private String name;
}
