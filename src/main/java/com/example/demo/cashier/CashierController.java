package com.example.demo.cashier;

import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.user.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cashier_room")
public class CashierController {
    private final CashierService cashierService;
    private final Cashiermapper cashiermapper;

    public CashierController(CashierService cashierService, Cashiermapper cashiermapper) {
        this.cashierService = cashierService;
        this.cashiermapper = cashiermapper;
    }

    @PostMapping("/save")
    public ResponseEntity<CashierDto> save(@RequestBody CashierRequest request){
        return new ResponseEntity<>(cashiermapper.toDto(cashierService.save(request)), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CashierDto>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(cashiermapper.fromPageEntity(cashierService.all(userDetails.getUserEntity())));
    }
    @PutMapping("/update")
    public ResponseEntity<CashierDto> update(@Valid @RequestBody CashierRequest obj){
        return ResponseEntity.ok().body(cashiermapper.toDto(cashierService.update(obj)));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<CashierDto> get(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(cashiermapper.toDto(cashierService.get(id)));
    }
    @GetMapping("/get_patient_dept/{patientid}")
    public ResponseEntity<HashMap<String,Float>> getPatientDept(@PathVariable("patientid") UUID patientid){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(cashierService.getPatientDept(userDetails.getUserEntity(),patientid));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(cashierService.delete(id));
    }

}
