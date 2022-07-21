package com.example.demo.history_worker_salary;

import com.example.demo.workers.WorkerFullDTO;
import com.example.demo.workers.WorkersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HistoryWorkerSalaryDto {
    private UUID id;
    private Float amount;
    private WorkerFullDTO worker;
    private Long startedDate;
    private Long endDate;

}
