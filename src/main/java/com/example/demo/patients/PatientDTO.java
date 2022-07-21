package com.example.demo.patients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private UUID id;
    @NotBlank(message = "Fullname must be filled because it is required!!!")
    private String fullname;
    private Long birthdate;
    private String mobile;
    @NotBlank(message = "Gender must be filled because it is required!!!")
    private String gender;

    private String region;

    private String destrict;

    private String area;
    @NotBlank(message = "Passport must be filled because it is required!!!")
    private String passport;




}
