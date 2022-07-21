package com.example.demo.user;

import com.example.demo.role.RoleEntity;
import com.example.demo.role.RoleRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private DataStatusEnum status;
    private UUID companyId;

}
