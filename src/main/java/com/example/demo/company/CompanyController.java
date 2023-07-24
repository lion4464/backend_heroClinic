package com.example.demo.company;


import com.example.demo.configuration.SwaggerUI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company")
@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
public class CompanyController {

    private final CompanyService companyService;
    private final Companymapper companymapper;
    public CompanyController(CompanyService companyService, Companymapper companymapper) {
        this.companyService = companyService;
        this.companymapper = companymapper;
    }

    @PostMapping("/save")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<CompanyResponse> save(@Valid @RequestBody CompanyRequest request) {
        return ResponseEntity.ok(companymapper.fromEntity(companyService.save(request)));
    }

    @GetMapping("/get/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<CompanyResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(companymapper.fromEntity(companyService.get(id)));
    }

    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<CompanyResponse> update(@Valid @RequestBody CompanyRequest request) {
        return ResponseEntity.ok(companymapper.fromEntity(companyService.update(request)));
    }
    @GetMapping("/all")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<CompanyResponse>> all(){
        return ResponseEntity.ok(companymapper.fromEntityList(companyService.all()));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(companyService.delete(id));
    }
}
