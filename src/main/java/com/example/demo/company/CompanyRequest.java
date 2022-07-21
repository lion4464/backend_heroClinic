package com.example.demo.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyRequest {

    private UUID id;
    @NotNull(message = "Company name is required!!!")
    private String name;
}
