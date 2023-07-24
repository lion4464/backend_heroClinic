package com.example.demo.analyses_invoice;


import com.example.demo.configuration.SwaggerUI;
import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.generic.PageableRequest;
import com.example.demo.generic.UUIDSpecification;
import com.example.demo.workers.WorkerFullDTO;
import com.example.demo.workers.WorkersEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.Date;
@RestController
@RequestMapping("/api/analyses_invoice")
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_USER')")
public class AnalysesInvoiceController {

 private final AnalysesInvoiceService analysesInvoiceService;
 private final AnalysesInvoiceMapper analysesInvoiceMapper;
 private final AnalyseInvoiceConvertor analyseInvoiceConvertor;

    public AnalysesInvoiceController(AnalysesInvoiceService analysesInvoiceService, AnalysesInvoiceMapper analysesInvoiceMapper, AnalyseInvoiceConvertor analyseInvoiceConvertor) {
        this.analysesInvoiceService = analysesInvoiceService;
        this.analysesInvoiceMapper = analysesInvoiceMapper;
        this.analyseInvoiceConvertor = analyseInvoiceConvertor;
    }
    @PostMapping("/save")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<AnalysesInvoiceResponse> save(@Valid @RequestBody AnalysesInvoiceRequest request){
        return new ResponseEntity<>(analysesInvoiceMapper.toDto(analysesInvoiceService.save(request)), HttpStatus.OK);
    }
    @PostMapping("/save-all")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<AnalysesInvoiceResponse>> saveAll(@Valid @RequestBody List<AnalysesInvoiceRequest> obj){
        return ResponseEntity.ok().body(analysesInvoiceMapper.fromEntityList(analysesInvoiceService.saveAll(obj)));
    }
    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<AnalysesInvoiceResponse> update(@Valid @RequestBody AnalysesInvoiceRequest obj){
        return ResponseEntity.ok().body(analysesInvoiceMapper.toDto(analysesInvoiceService.update(obj)));
    }
    @GetMapping("get/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<AnalysesInvoiceResponse> get(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(analysesInvoiceMapper.toDto(analysesInvoiceService.get(id)));
    }
    @PostMapping("/pageable")
    public ResponseEntity<Page<AnalysesInvoiceResponse>> pageable(@RequestBody PageableRequest pageable){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageRequest pageRequest = PageRequest.of(
                pageable.getPage(),
                pageable.getPerPage(),
                Sort.Direction.fromString(pageable.getSort().getDirection()),
                pageable.getSort().getName()
        );
        return ResponseEntity.ok(analyseInvoiceConvertor.createFromEntities(analysesInvoiceService.all(
                new UUIDSpecification<AnalysesInvoiceEntity>("companyId",userDetails.getUserEntity().getCompanyId())
                        .and(new SearchSpecification(pageable.getSearch())), pageRequest)
        ));
    }

    @GetMapping("/get_count")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<AnalyseInvoiseCounterDto>> getAllInvoicesBetweenDate(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to)
    {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(analysesInvoiceService.getCountInvoicesBetweenDates(userDetails.getUserEntity(),from,to));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(analysesInvoiceService.delete(id));
    }

}

