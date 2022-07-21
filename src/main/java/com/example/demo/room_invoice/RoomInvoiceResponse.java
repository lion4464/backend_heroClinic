package com.example.demo.room_invoice;

import com.example.demo.cashier.CashierForRoomInvoice;
import com.example.demo.department.DepartmentDto;
import com.example.demo.generic.PaymentType;
import com.example.demo.patients.PatientDTO;
import com.example.demo.room_place.RoomPlaceResponce;
import com.example.demo.workers.WorkerFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomInvoiceResponse {
    private UUID id;
    private PatientDTO patient;
    private RoomPlaceResponce roomPlace;
    private CashierForRoomInvoice cashier;
    private WorkerFullDTO worker;
    private Integer discount;
    private Long delay;
    private Float dept;
    private DepartmentDto department;
    protected UUID createdBy;
    private Long modifiedDate;
    private Long createdDate;
    private PaymentType paymentType;

    public RoomInvoiceResponse(UUID id, PatientDTO patient, RoomPlaceResponce roomPlace,
                               CashierForRoomInvoice cashier, WorkerFullDTO worker,
                               int discount, Long delay,
                               Float dept, PaymentType paymentType,
                               DepartmentDto department,
                               UUID createdBy, Long modifiedDate,
                               Long createdDate) {
        this.id=id;
        this.patient=patient;
        this.roomPlace=roomPlace;
        this.cashier=cashier;
        this.worker=worker;
        this.discount=discount;
        this.delay=delay;
        this.dept=dept;
        this.paymentType=paymentType;
        this.department=department;
        this.createdBy=createdBy;
        this.modifiedDate=modifiedDate;
        this.createdDate=createdDate;
    }
}
