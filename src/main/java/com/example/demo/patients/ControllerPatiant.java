package com.example.demo.patients;

import com.example.demo.configuration.SwaggerUI;
import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.generic.DeletedSpecification;
import com.example.demo.generic.PageableRequest;

import com.example.demo.generic.UUIDSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Security;
import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_USER')")
@RequestMapping("/api/patient")
public class ControllerPatiant {
    private final PatientService patientService;
    private final Patientmapper patientmapper;
    private final PatientConvertor patientConvertor;


    public ControllerPatiant(PatientService patientService, Patientmapper patientmapper, PatientConvertor patientConvertor) {
        this.patientService = patientService;
        this.patientmapper = patientmapper;
        this.patientConvertor = patientConvertor;
    }
    @GetMapping("/get/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<PatientDTO> getid(@PathVariable("id") UUID id){
            return ResponseEntity.ok(patientmapper.toDto(patientService.getId(id)));
   }

    @PostMapping("/save")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<PatientDTO> insert(@Valid @RequestBody PatientDTO obj){
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return  ResponseEntity.ok(patientmapper.toDto(patientService.insert(userDetails.getUserEntity(),patientmapper.fromDto(obj))));
    }
    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<PatientDTO> update(@Valid @RequestBody PatientDTO obj){

        return ResponseEntity.ok(patientmapper.toDto(patientService.update(patientmapper.fromDto(obj))));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(patientService.delete(id));
    }
    @PostMapping("/pageable")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<Page<PatientDTO>> pageable(@RequestBody PageableRequest pageable){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageRequest pageRequest = PageRequest.of(
                pageable.getPage(),
                pageable.getPerPage(),
                Sort.Direction.fromString(pageable.getSort().getDirection()),
                pageable.getSort().getName()
        );
        return ResponseEntity.ok(patientConvertor.createFromEntities(patientService.all(
                new SearchSpecification(pageable.getSearch())
                        .and(new UUIDSpecification<PatientEntity>("companyId",userDetails.getUserEntity().getCompanyId())
                                .and(new DeletedSpecification<>("deleted",false))), pageRequest)
                )
        );
    }




}
