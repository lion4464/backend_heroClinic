package com.example.demo.analyses;

import com.example.demo.workers.WorkerFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnalysesResponse {
    private UUID id;
    private String name;
    private Float payAmount;
    private WorkerFullDTO worker;
    protected UUID createdBy;
    private Long modifiedDate;
    private Long createdDate;
}
