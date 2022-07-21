package com.example.demo.department;

import com.example.demo.configuration.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_USER')")
@RequestMapping("/api/department")
public class ControllerDepartment {
    private final DepartmentService departmentService;
    private final Departmentmapper departmentmapper;

    public ControllerDepartment(DepartmentService departmentService, Departmentmapper departmentmapper) {
        this.departmentService = departmentService;
        this.departmentmapper = departmentmapper;
    }


    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> save(@RequestBody DepartmentRequest request){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return new ResponseEntity<>(departmentmapper.toDto(departmentService.saveRole(userDetails.getUserEntity(),request)), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDto>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(departmentmapper.fromPageEntity(departmentService.all(userDetails.getUserEntity())));
    }
    @PutMapping("/update")
    public ResponseEntity<DepartmentDto> update(@Valid @RequestBody DepartmentRequest obj){
        return ResponseEntity.ok().body(departmentmapper.toDto(departmentService.update(obj)));
    }
    @GetMapping("get/{id}")
    public ResponseEntity<DepartmentDto> get(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(departmentmapper.toDto(departmentService.get(id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(departmentService.delete(id));
    }
}
