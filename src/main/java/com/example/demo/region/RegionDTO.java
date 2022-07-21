package com.example.demo.region;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {
    private Long id;

    @NotBlank(message = "Name_oz must be because is required!!!!")
    private String nameOz;

    @NotBlank(message = "Name_ru must be because is required!!!!")
    private String nameRu;
}
