package com.example.demo.review_invoice;

import com.example.demo.workers.WorkerFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInvoiceCount {
    private WorkerFullDTO workerFullDTO;
    private Integer count;
}
