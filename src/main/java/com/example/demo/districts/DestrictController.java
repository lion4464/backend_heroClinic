package com.example.demo.districts;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/destrict")
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REGISTRATION')")
public class DestrictController {
    private  DestrictService destrictService;
    private  DestrictMapper destrictMapper;

    public DestrictController(DestrictService destrictService, DestrictMapper destrictMapper) {
        this.destrictService = destrictService;
        this.destrictMapper = destrictMapper;
    }
    @GetMapping("/all")
    public ResponseEntity<List<DestrictDTO>> all(){
        return ResponseEntity.ok().body(destrictMapper.fromEntityList(destrictService.all()));
    }
    @PutMapping("/update")
    public ResponseEntity<DestrictDTO> update(@Valid @RequestBody DestrictDTO obj){
        return ResponseEntity.ok().body(destrictMapper.fromEntity(destrictService.update(obj)));
    }
}
