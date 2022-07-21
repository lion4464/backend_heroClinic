package com.example.demo.analyses_invoice;

import com.example.demo.workers.WorkerFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseInvoiseCounterDto {
    private Integer count;
    private WorkerFullDTO workerFullDTO;
}
