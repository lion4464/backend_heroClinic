package com.example.demo.area;

import com.example.demo.districts.DestrictDTO;
import com.example.demo.region.RegionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDTO {
    private List<RegionDTO> regions;
    private List<DestrictDTO> destricts;
}
