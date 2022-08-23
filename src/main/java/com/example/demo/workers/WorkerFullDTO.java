package com.example.demo.workers;

import com.example.demo.department.DepartmentMinDTO;
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
public class WorkerFullDTO {
    private UUID id;
    private String fullname;
    private String mobile;
    private String area;
    private String region;
    private String destrict;
    private String gender;
    private Long birthdate;
    private boolean deleted;
    private Long startedDate;
    private Long expiredDate;
    private DepartmentMinDTO department;
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
