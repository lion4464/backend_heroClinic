package com.example.demo.workers;

import com.example.demo.configuration.SwaggerUI;
import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.history_worker_salary.HistoryWorkerSalaryDto;
import com.example.demo.history_worker_salary.HistoryWorkerSalaryService;
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
@RequestMapping("/api/worker")
@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
public class ControllerWorker {
    private final WorkerService workerService;
    private final WorkerMapper workerMapper;
    private final HistoryWorkerSalaryService historyWorkerSalaryService;

    public ControllerWorker(WorkerService workerService, WorkerMapper workerMapper, HistoryWorkerSalaryService historyWorkerSalaryService) {
        this.workerService = workerService;
        this.workerMapper = workerMapper;
        this.historyWorkerSalaryService = historyWorkerSalaryService;
    }
    @PostMapping("/save")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<WorkerFullDTO> save(@Valid @RequestBody WorkerRequest request) throws DataNotFoundException {
        return new ResponseEntity<>(workerMapper.toDto(workerService.saveWorker(request)), HttpStatus.OK);
    }
    @GetMapping("/all_active_status")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<WorkerFullDTO>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(workerMapper.fromPageEntity(workerService.findAllActiveStatus(userDetails.getUserEntity())));
    }
    @GetMapping("/all_inactive_status")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<WorkerFullDTO>> inactivestatus(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(workerMapper.fromPageEntity(workerService.findAllInactiveStatus(userDetails.getUserEntity())));
    }
    @GetMapping("/about/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<HistoryWorkerSalaryDto>> getAbout(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(historyWorkerSalaryService.findByWorkerId(id));
    }
    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<WorkerFullDTO> update(@Valid @RequestBody WorkerRequest obj) throws DataNotFoundException {
        return ResponseEntity.ok().body(workerMapper.toDto(workerService.updateWorker(obj)));
    }
    @GetMapping("/get/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<WorkerFullDTO> get(@PathVariable("id") UUID id) throws DataNotFoundException {
        return ResponseEntity.ok().body(workerMapper.toDto(workerService.findById(id)));
    }
    @DeleteMapping("/delete/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        workerService.delete(id);
        return ResponseEntity.ok("OK");
    }
}
