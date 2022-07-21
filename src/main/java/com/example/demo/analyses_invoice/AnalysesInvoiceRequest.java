package com.example.demo.analyses_invoice;

import com.example.demo.generic.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnalysesInvoiceRequest {
    private UUID id;
    @NotNull(message = "Patient is required!!!")
    private UUID patientId;
    @NotNull(message = "Analyse is required!!!")
    private UUID analyseId;

    private Integer discount;
    @NotNull(message = "Type is required!!!")
    private PaymentType paymentType;



}
