package com.example.demo.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    private UUID id;
    @NotBlank(message = "Name must be filled because it is reuired!!!")
    private String name;

}
