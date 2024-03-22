package com.example.demo.room_invoice;

import com.example.demo.cashier.Cashiermapper;
import com.example.demo.department.Departmentmapper;
import com.example.demo.generic.Convertor;
import com.example.demo.patient_dept.PatientDeptmapper;
import com.example.demo.patients.PatientConvertor;
import com.example.demo.room_place.RoomPlaceMapper;
import com.example.demo.workers.WorkerMapper;
import org.springframework.stereotype.Component;

@Component
public class RoomInvoiceConvertor extends Convertor<RoomInvoiceResponse, RoomInvoiceEntity> {
    public RoomInvoiceConvertor(PatientConvertor patientConvertor, Cashiermapper cashiermapper, WorkerMapper workerMapper, RoomPlaceMapper roomPlaceMapper, PatientDeptmapper patientDeptmapper, Departmentmapper departmentmapper) {
        super(roomInvoiceResponse-> new RoomInvoiceEntity(),roomInvoiceEntity -> new RoomInvoiceResponse(
                roomInvoiceEntity.getId(),
                patientConvertor.convertFromEntity(roomInvoiceEntity.getPatient()),
                roomPlaceMapper.toDto(roomInvoiceEntity.getRoomPlace()),
                cashiermapper.toForRoomInvoice(roomInvoiceEntity.getCashier()),
                workerMapper.toDto(roomInvoiceEntity.getWorker()),
                0,
                roomInvoiceEntity.getRoomPlace().getClosedDate(),
                patientDeptmapper.toDto(roomInvoiceEntity.getPatientDept()).getSum(),
                roomInvoiceEntity.getPaymentType(),
                departmentmapper.toDto(roomInvoiceEntity.getDepartment()),
                roomInvoiceEntity.getCreatedBy(),
                roomInvoiceEntity.getModifiedDate(),
                roomInvoiceEntity.getCreatedDate()
        ));
    }
}
