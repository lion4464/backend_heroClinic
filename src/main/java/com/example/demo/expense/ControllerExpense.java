package com.example.demo.expense;

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
@RequestMapping("/api/expense")
public class ControllerExpense {
    private final ExpenseService expenseService;
    private final Expensemapper expensemapper;

    public ControllerExpense(ExpenseService expenseService, Expensemapper expensemapper) {
        this.expenseService = expenseService;
        this.expensemapper = expensemapper;
    }


    @PostMapping("/save")
    public ResponseEntity<ExpenseDto> save(@Valid @RequestBody ExpenseRequest request){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(expensemapper.toDto(expenseService.save(userDetails.getUserEntity(),request)), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ExpenseDto>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(expensemapper.fromPageEntity(expenseService.all(userDetails.getUserEntity())));
    }
    @PutMapping("/update")
    public ResponseEntity<ExpenseDto> update(@Valid @RequestBody ExpenseRequest obj){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(expensemapper.toDto(expenseService.update(userDetails.getUserEntity(),obj)));
    }
    @GetMapping("get/{id}")
    public ResponseEntity<ExpenseDto> get(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(expensemapper.toDto(expenseService.get(id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(expenseService.delete(id));
    }



}
