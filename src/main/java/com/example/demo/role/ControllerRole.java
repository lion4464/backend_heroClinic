package com.example.demo.role;

import com.example.demo.configuration.SwaggerUI;
import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.patients.PatientDTO;
import com.example.demo.user.UserDto;
import com.example.demo.user.UserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/role")
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_USER')")
public class ControllerRole {
    private final RoleService roleService;
    private final Rolemapper rolemapper;

    public ControllerRole(RoleService roleService, Rolemapper rolemapper) {
        this.roleService = roleService;
        this.rolemapper = rolemapper;
    }

    @GetMapping("/all")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<RoleDto>> all(){
         return ResponseEntity.ok().body(rolemapper.fromPageEntity(roleService.allRole()));
    }
    @GetMapping("get/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<RoleDto> get(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(rolemapper.toDto(roleService.get(id)));
    }

}
