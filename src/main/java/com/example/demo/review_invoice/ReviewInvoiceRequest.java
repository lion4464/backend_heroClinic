package com.example.demo.review_invoice;

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
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInvoiceRequest {
    private UUID id;
    @NotNull(message = "Patient is required!!!")
    private UUID patientId;
    @NotNull(message = "Analyse is required!!!")
    private UUID workerId;

    private Integer discount;
    @NotNull(message = "Payment Type is required")
    private PaymentType paymentType;
    @NotNull(message = "Department is required!!!")
    private UUID departmentId;
    @NotBlank(message = "Status is required!!!")
    private String status;

}
