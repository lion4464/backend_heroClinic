package com.example.demo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequest {
    @NotBlank(message = "UserName is required!!!")
    private String username;
    @NotBlank(message = "Password is required!!!")
    private String password;
    @NotBlank(message = "FirstName is required!!!")
    private String firstName;
    private String lastName;
    private String phone;
    @NotNull(message = "Status is required!!!")
    private DataStatusEnum status;
    @NotBlank(message = "Role name is required!!!")
    private String roleName;
    @NotNull(message = "companyId is required!!!")
    private UUID companyId;
}
