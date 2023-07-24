package com.example.demo.patient_dept;

import com.example.demo.configuration.SwaggerUI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patient_dept")
public class PatientDeptController {
    private final PatientDeptService patientDeptService;
    private final PatientDeptmapper patientDeptmapper;

    public PatientDeptController(PatientDeptService patientDeptService, PatientDeptmapper patientDeptmapper) {
        this.patientDeptService = patientDeptService;
        this.patientDeptmapper = patientDeptmapper;
    }

    @PostMapping("/save")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<PatientDeptDto> save(@RequestBody PatientDeptRequest request){
        return new ResponseEntity<>(patientDeptmapper.toDto(patientDeptService.save(request)), HttpStatus.OK);
    }
    @GetMapping("/all")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<PatientDeptDto>> all(){
        return ResponseEntity.ok().body(patientDeptmapper.fromPageEntity(patientDeptService.all()));
    }
    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<PatientDeptDto> update(@Valid @RequestBody PatientDeptRequest obj){
        return ResponseEntity.ok().body(patientDeptmapper.toDto(patientDeptService.update(obj)));
    }
    @GetMapping("get/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<PatientDeptDto> get(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(patientDeptmapper.toDto(patientDeptService.get(id)));
    }
    @DeleteMapping("/delete/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(patientDeptService.delete(id));
    }

}
