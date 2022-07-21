package com.example.demo.region;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/region")
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REGISTRATION')")
public class RegionController {
    private final RegionService regionService;
    private final RegionMapper regionMapper;

    public RegionController(RegionService regionService, RegionMapper regionMapper) {
        this.regionService = regionService;
        this.regionMapper = regionMapper;
    }
    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> all(){
        return ResponseEntity.ok().body(regionMapper.fromEntityList(regionService.all()));
    }
    @PutMapping("/update")
    public ResponseEntity<RegionDTO> update(@Valid @RequestBody RegionDTO obj){
        return ResponseEntity.ok().body(regionMapper.fromEntity(regionService.update(obj)));
    }
}
