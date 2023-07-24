package com.example.demo.role_worker;

import com.example.demo.configuration.SwaggerUI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/role_for_ui")
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REGISTRATION','ROLE_CASHIER','ROLE_SUPER_REGISTRATION','ROLE_DOCTOR','ROLE_DIRECTOR','ROLE_SUPER_ADMIN')")
public class GetRoleForUIController {

    @GetMapping
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public String getRole(Authentication authentication) {
            return authentication.getAuthorities().toString();
    }

}
