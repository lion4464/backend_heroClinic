package com.example.demo.room_invoice;

import com.example.demo.patient_dept.PatientDeptEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.data.domain.Page;

import java.util.List;
@Mapper(componentModel = "spring",nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface RoomInvoiceMapper {
    @Named("patientAllDept")
    static Float patientAllDept(PatientDeptEntity patientDept) {
        return patientDept.getSum();
    }

    @Mapping(source = "patientDept",target = "dept",qualifiedByName = "patientAllDept")
    RoomInvoiceResponse toDto(RoomInvoiceEntity roomInvoiceEntity);
    RoomInvoiceEntity fromDto(RoomInvoiceResponse roomInvoiceResponse);
    List<RoomInvoiceResponse> fromPageEntity(Page<RoomInvoiceEntity> page);
    List<RoomInvoiceResponse> fromEntityList(List<RoomInvoiceEntity> saveAll);
}
