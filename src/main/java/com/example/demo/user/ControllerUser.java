package com.example.demo.user;

import com.example.demo.configuration.SwaggerUI;
import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.exceptions.DataNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REGISTRATION','ROLE_CASHIER','ROLE_SUPER_REGISTRATION','ROLE_DOCTOR','ROLE_DIRECTOR','ROLE_SUPER_ADMIN')")
public class ControllerUser {
    private final UserService userService;
    private final Usermapper usermapper;

    public ControllerUser(UserService userService, Usermapper usermapper) {
        this.userService = userService;
        this.usermapper = usermapper;
    }
    @PutMapping("/change-password")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<HashMap<String, Object>> changePassword(@Valid @RequestBody PasswordRequest request) throws DataNotFoundException, NoSuchAlgorithmException {
        return ResponseEntity.ok(userService.changePassword(request.getUsername(), request));
    }


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    @GetMapping("/get_status_active")
    public ResponseEntity<List<HashMap<String,Object>>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return ResponseEntity.ok().body(userService.getAllUsers(userDetails.getUserEntity(),DataStatusEnum.ACTIVE));
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    @GetMapping("/get_status_inactive")
    public ResponseEntity<List<HashMap<String,Object>>> allInactive(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(userService.getAllUsers(userDetails.getUserEntity(),DataStatusEnum.INACTIVE));
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    @PutMapping("/change-status/{id}")
    public ResponseEntity<UserDto> changeStatus(@PathVariable("id") UUID id,
     @Valid @RequestBody UserRequest request) throws DataNotFoundException {
        UserEntity model = userService.changeStatus(id, request);
        return ResponseEntity.ok(usermapper.toDto(model));
    }





}
