package com.example.demo.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {
    private UUID id;
    private String name;
    private Boolean deleted;
    private Long createdDate;
    private Long modifiedDate;
}
