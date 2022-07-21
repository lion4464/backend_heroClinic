package com.example.demo.user;

import com.example.demo.exceptions.DataNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<HashMap<String, Object>> changePassword(@Valid @RequestBody PasswordRequest request) throws DataNotFoundException, NoSuchAlgorithmException {
        return ResponseEntity.ok(userService.changePassword(request.getUsername(), request));
    }


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<HashMap<String,Object>>> all(){
    return ResponseEntity.ok().body(userService.getAllUsers());
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/change-status/{id}")
    public ResponseEntity<UserDto> changeStatus(@PathVariable("id") UUID id,
     @Valid @RequestBody UserRequest request) throws DataNotFoundException {
        UserEntity model = userService.changeStatus(id, request);
        return ResponseEntity.ok(usermapper.toDto(model));
    }





}
