package com.example.demo.districts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestrictDTO {
    private Long id;

    @NotBlank(message = "Name_oz must be because is required!!!!")
    private String nameOz;
    @NotNull(message = "Region_Id must be because is required!!!!")
    private Long regionId;
    @NotBlank(message = "Name_ru must be because is required!!!!")
    private String nameRu;
}
