package com.example.demo.review_invoice;

import com.example.demo.configuration.SwaggerUI;
import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.PageableRequest;
import com.example.demo.generic.UUIDSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_USER')")
@RequestMapping("/api/review")
public class ReviewInvoiceController {

 private final ReviewInvoiceService reviewInvoiceService;
 private final ReviewInvoicemapper reviewInvoicemapper;
 private final ReviewInvoiceConvertor reviewInvoiceConvertor;


    public ReviewInvoiceController(ReviewInvoiceService reviewInvoiceService, ReviewInvoicemapper reviewInvoicemapper, ReviewInvoiceConvertor reviewInvoiceConvertor) {
        this.reviewInvoiceService = reviewInvoiceService;
        this.reviewInvoicemapper = reviewInvoicemapper;
        this.reviewInvoiceConvertor = reviewInvoiceConvertor;
    }

    @GetMapping("/get/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<ReviewInvoiceDTO> getid(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(reviewInvoicemapper.toDto(reviewInvoiceService.getId(id)));
    }

    @PostMapping("/save")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<ReviewInvoiceDTO> insert(@Valid @RequestBody ReviewInvoiceRequest obj) throws DataNotFoundException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(reviewInvoicemapper.toDto(reviewInvoiceService.save(obj,userDetails.getUserEntity())));
    }

    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<ReviewInvoiceDTO> update(@Valid @RequestBody ReviewInvoiceRequest obj) {
        return ResponseEntity.ok(reviewInvoicemapper.toDto(reviewInvoiceService.update(obj)));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(reviewInvoiceService.delete(id));
    }
    @PostMapping("/pageable")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<Page<ReviewInvoiceDTO>> pageable(@RequestBody PageableRequest pageable){
        PageRequest pageRequest = PageRequest.of(
                pageable.getPage(),
                pageable.getPerPage(),
                Sort.Direction.fromString(pageable.getSort().getDirection()),
                pageable.getSort().getName()
        );
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(reviewInvoiceConvertor.createFromEntities(reviewInvoiceService.all(
                new SearchSpecification(pageable.getSearch())
              .and(new UUIDSpecification<>("companyId",userDetails.getUserEntity().getCompanyId())),
                pageRequest)));

    }

    @GetMapping("/reviews_count")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<ReviewInvoiceCount>> getCountReviews(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(reviewInvoiceService.getReviewsCount(from, to,userDetails.getUserEntity()));
    }

}
