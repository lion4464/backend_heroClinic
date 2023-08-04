package com.example.demo.analyses;

import com.example.demo.analyses_invoice.AnalysesInvoiceEntity;
import com.example.demo.configuration.SwaggerUI;
import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.DeletedSpecification;
import com.example.demo.generic.PageableRequest;
import com.example.demo.generic.SearchSpecification;
import com.example.demo.generic.UUIDSpecification;
import com.example.demo.patients.PatientDTO;
import com.example.demo.patients.PatientEntity;
import com.example.demo.user.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/analyse")
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REGISTRATION')")
public class AnalysesController {
 private final AnalysesService analysesService;
 private final AnalysesMapper analysesMapper;

    public AnalysesController(AnalysesService analysesService, AnalysesMapper analysesMapper) {
        this.analysesService = analysesService;
        this.analysesMapper = analysesMapper;
    }
    @PostMapping("/save")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<AnalysesResponse> save(@Valid @RequestBody AnalysesRequest request) throws DataNotFoundException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(analysesMapper.toDto(analysesService.saveAnalyse(userDetails.getUserEntity(),request)), HttpStatus.OK);
    }
    @GetMapping("/all")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<AnalysesResponse>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(analysesMapper.toDtoList(analysesService.getAll(userDetails.getUserEntity())));
    }
    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<AnalysesResponse> update(@Valid @RequestBody AnalysesRequest obj) throws DataNotFoundException {
        return ResponseEntity.ok().body(analysesMapper.toDto(analysesService.updateAnalayse(obj)));
    }
    @PutMapping("/update-all")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<AnalysesResponse>> update(@Valid @RequestBody List<AnalysesRequest> obj) throws DataNotFoundException {
        return ResponseEntity.ok().body(analysesMapper.toDtoList(analysesService.updateAll(obj)));
    }
    @GetMapping("get/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<AnalysesResponse> get(@PathVariable("id") UUID id) throws DataNotFoundException {
        return ResponseEntity.ok().body(analysesMapper.toDto(analysesService.findById(id)));
    }
    @DeleteMapping("/delete/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        analysesService.delete(id);
        return ResponseEntity.ok("OK");
    }
    @PostMapping("/pageable")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<Page<AnalysesResponse>> pageable(@RequestBody PageableRequest pageable){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageRequest pageRequest = PageRequest.of(
                pageable.getPage(),
                pageable.getPerPage(),
                Sort.Direction.fromString(pageable.getSort().getDirection()),
                pageable.getSort().getName()
        );
        return ResponseEntity.ok(analysesMapper.toDtoPage(analysesService.findAll
                        (new UUIDSpecification<AnalysesEntity>("companyId",userDetails.getUserEntity().getCompanyId())
                                        .and(new SearchSpecification<>(pageable.getSearch())), pageRequest
                        )
        ));
    }
}
