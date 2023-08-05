package com.example.demo.salary_type;

import com.example.demo.configuration.SwaggerUI;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.salary_type.SalaryTypeService;
import com.example.demo.salary_type.SalaryTypemapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_USER')")
@RequestMapping("/api/salary_type")
public class ControllerSalaryType {
    private final SalaryTypeService salaryService;
    private final SalaryTypemapper salaryTypemapper;

    public ControllerSalaryType(SalaryTypeService salaryService, SalaryTypemapper salaryTypemapper) {
        this.salaryService = salaryService;
        this.salaryTypemapper = salaryTypemapper;
    }


    @GetMapping("/all")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<SalaryTypeDto>> all(){
        return ResponseEntity.ok().body(salaryTypemapper.fromPageEntity(salaryService.all()));
    }
    @GetMapping("get/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<SalaryTypeDto> get(@PathVariable("id") UUID id) throws DataNotFoundException {
        return ResponseEntity.ok().body(salaryTypemapper.toDto(salaryService.get(id)));
    }



}
