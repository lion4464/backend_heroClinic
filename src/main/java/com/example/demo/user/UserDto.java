package com.example.demo.user;

import com.example.demo.role.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private List<RoleDto> role;
    private UUID companyId;
}
