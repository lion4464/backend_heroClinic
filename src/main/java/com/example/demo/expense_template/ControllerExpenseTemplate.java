package com.example.demo.expense_template;

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
@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
@RequestMapping("/api/expense_template")
public class ControllerExpenseTemplate {
    private final ExpenseTemplateService expenseTemplateService;
    private final ExpenseTemplatemapper expenseTemplatemapper;

    public ControllerExpenseTemplate(ExpenseTemplateService expenseTemplateService, ExpenseTemplatemapper expenseTemplatemapper) {
        this.expenseTemplateService = expenseTemplateService;
        this.expenseTemplatemapper = expenseTemplatemapper;
    }


    @PostMapping("/save")
    public ResponseEntity<ExpenseTemplateDto> save(@Valid @RequestBody ExpenseTemplateRequest request){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(expenseTemplatemapper.toDto(expenseTemplateService.saveRole(userDetails.getUserEntity(),request)), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ExpenseTemplateDto>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(expenseTemplatemapper.fromPageEntity(expenseTemplateService.all(userDetails.getUserEntity())));
    }
    @PutMapping("/update")
    public ResponseEntity<ExpenseTemplateDto> update(@Valid @RequestBody ExpenseTemplateRequest obj){
        return ResponseEntity.ok().body(expenseTemplatemapper.toDto(expenseTemplateService.update(obj)));
    }
    @GetMapping("get/{id}")
    public ResponseEntity<ExpenseTemplateDto> get(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(expenseTemplatemapper.toDto(expenseTemplateService.get(id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(expenseTemplateService.delete(id));
    }



}
