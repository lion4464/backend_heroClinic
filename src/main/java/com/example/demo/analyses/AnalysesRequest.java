package com.example.demo.analyses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnalysesRequest {
    private UUID id;

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "pay amount is required")
    private Float payAmount;
    @NotNull(message = "worker is required")
    private UUID workerId;
}
