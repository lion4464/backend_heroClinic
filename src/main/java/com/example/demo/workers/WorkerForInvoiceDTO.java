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
public class WorkerForInvoiceDTO {
    private UUID id;
    private String fullname;
    private String mobile;
    private String region;
    private String destrict;
    private String area;
    private String gender;
    private Long birthdate;
    private Long startedDate;
    private SalaryMinDTO salaryType;
    private Float reviewAmount;
    private Float salaryAmount;
    private String position;
    private String keyId;
    private Integer room;
    private DataStatusEnum status;
    protected UUID createdBy;
    private Long modifiedDate;
    private Long createdDate;
}
