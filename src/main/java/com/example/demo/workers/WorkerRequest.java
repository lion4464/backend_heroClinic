package com.example.demo.workers;

import com.example.demo.generic.DataStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkerRequest {
    private UUID id;
    @NotBlank(message = "Fullname must be filled because it is reuired!!!")
    private String fullname;
    private String mobile;
    private String region;
    private String destrict;
    private String area;
    private String gender;
    private Long birthdate;
    private Long startedDate;
    private Long expiredDate;
    @NotNull(message = "Department must be filled because it is reuired!!!")
    private UUID departmentId;
    @NotNull(message = "SalaryType must be filled because it is reuired!!!")
    private UUID salaryTypeId;
    @NotNull(message = "ReviewAmount must be filled because it is reuired!!!")
    private Float reviewAmount;
    @NotNull(message = "SalaryAmount must be filled because it is reuired!!!")
    private Float salaryAmount;
    @NotBlank(message = "UniqueKey must be filled because it is reuired!!!")
    private String keyId;
    @NotBlank(message = "Position must be filled because it is reuired!!!")
    private String position;
    private Integer room;
    private DataStatusEnum status;
//    protected UUID createdBy;
//    private Long modifiedDate;
//    private Long createdDate;
}
