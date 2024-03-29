package com.example.demo.worker_expense;

import com.example.demo.configuration.SwaggerUI;
import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.exceptions.DataNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/api/worker_expense")
public class ControllerWorkerExpense {
    private final WorkerExpenseService workerExpenseService;
    private final WorkerExpensemapper workerExpensemapper;

    public ControllerWorkerExpense(WorkerExpenseService workerExpenseService, WorkerExpensemapper workerExpensemapper) {
        this.workerExpenseService = workerExpenseService;
        this.workerExpensemapper = workerExpensemapper;
    }


    @PostMapping("/save")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<WorkerExpenseDto> save(@RequestBody WorkerExpenseRequest request) throws DataNotFoundException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(workerExpensemapper.toDto(workerExpenseService.save(request,userDetails.getUserEntity())), HttpStatus.OK);
    }
    @GetMapping("/all")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<WorkerExpenseDto>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(workerExpensemapper.fromPageEntity(workerExpenseService.all(userDetails.getUserEntity())));
    }
    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<WorkerExpenseDto> update(@Valid @RequestBody WorkerExpenseRequest obj) throws DataNotFoundException {
        return ResponseEntity.ok().body(workerExpensemapper.toDto(workerExpenseService.update(obj)));
    }
    @GetMapping("get/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<WorkerExpenseDto> get(@PathVariable("id") UUID id) throws DataNotFoundException {
        return ResponseEntity.ok().body(workerExpensemapper.toDto(workerExpenseService.get(id)));
    }
    @DeleteMapping("/delete/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(workerExpenseService.delete(id));
    }



}
