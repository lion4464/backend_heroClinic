package com.example.demo.area;

import com.example.demo.configuration.SwaggerUI;
import com.example.demo.districts.DestrictMapper;
import com.example.demo.districts.DestrictService;
import com.example.demo.region.RegionMapper;
import com.example.demo.region.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/area")
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REGISTRATION')")
public class AreaController {
    private final RegionService regionService;
    private final DestrictService districtService;
    private final RegionMapper regionMapper;
    private final DestrictMapper destrictMapper;

    public AreaController(RegionService regionService, DestrictService districtService, RegionMapper regionMapper, DestrictMapper destrictMapper) {
        this.regionService = regionService;
        this.districtService = districtService;
        this.regionMapper = regionMapper;
        this.destrictMapper = destrictMapper;
    }
    @GetMapping("/all")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<AreaDTO>> getArea(){
        List<AreaDTO> result = new ArrayList<>();
        result.add(new AreaDTO(regionMapper.fromEntityList(regionService.all()),destrictMapper.fromEntityList(districtService.all())));
    return ResponseEntity.ok(result);
    }
}
