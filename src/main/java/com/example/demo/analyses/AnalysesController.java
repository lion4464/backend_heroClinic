package com.example.demo.analyses;

import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.user.UserEntity;
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
    public ResponseEntity<AnalysesResponse> save(@Valid @RequestBody AnalysesRequest request){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(analysesMapper.toDto(analysesService.save(userDetails.getUserEntity(),request)), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<AnalysesResponse>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(analysesMapper.fromPageEntity(analysesService.all(userDetails.getUserEntity())));
    }
    @PutMapping("/update")
    public ResponseEntity<AnalysesResponse> update(@Valid @RequestBody AnalysesRequest obj){
        return ResponseEntity.ok().body(analysesMapper.toDto(analysesService.update(obj)));
    }
    @PutMapping("/update-all")
    public ResponseEntity<List<AnalysesResponse>> update(@Valid @RequestBody List<AnalysesRequest> obj){
        return ResponseEntity.ok().body(analysesMapper.fromPageEntity(analysesService.updateAll(obj)));
    }
    @GetMapping("get/{id}")
    public ResponseEntity<AnalysesResponse> get(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(analysesMapper.toDto(analysesService.get(id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(analysesService.delete(id));
    }
}
