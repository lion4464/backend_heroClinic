package com.example.demo.region;

import com.example.demo.configuration.SwaggerUI;
import com.example.demo.exceptions.DataNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<RegionDTO>> all(){
        return ResponseEntity.ok().body(regionMapper.fromEntityList(regionService.all()));
    }
    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<RegionDTO> update(@Valid @RequestBody RegionDTO obj) throws DataNotFoundException {
        return ResponseEntity.ok().body(regionMapper.fromEntity(regionService.update(obj)));
    }
}
