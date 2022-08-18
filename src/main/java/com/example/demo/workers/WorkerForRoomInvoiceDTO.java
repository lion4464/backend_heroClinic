package com.example.demo.workers;

import com.example.demo.generic.DataStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkerForRoomInvoiceDTO {
    private UUID id;
    private String fullname;
    private String mobile;
    private DataStatusEnum status;

}
