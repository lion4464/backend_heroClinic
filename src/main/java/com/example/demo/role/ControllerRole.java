package com.example.demo.role;

import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.patients.PatientDTO;
import com.example.demo.user.UserDto;
import com.example.demo.user.UserRequest;
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
public class ControllerRole {
    private final RoleService roleService;
    private final Rolemapper rolemapper;

    public ControllerRole(RoleService roleService, Rolemapper rolemapper) {
        this.roleService = roleService;
        this.rolemapper = rolemapper;
    }

    @PostMapping("/save")
    public ResponseEntity<RoleDto> save(@RequestBody RoleRequest request){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(rolemapper.toDto(roleService.saveRole(request,userDetails.getUserEntity())), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<RoleDto>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(rolemapper.fromPageEntity(roleService.allRole(userDetails.getUserEntity())));
    }
    @PutMapping("/update")
    public ResponseEntity<RoleDto> update(@Valid @RequestBody RoleRequest obj){
        return ResponseEntity.ok().body(rolemapper.toDto(roleService.updateRole(obj)));
    }
    @GetMapping("get/{id}")
    public ResponseEntity<RoleDto> get(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(rolemapper.toDto(roleService.get(id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(roleService.delete(id));
    }

}
