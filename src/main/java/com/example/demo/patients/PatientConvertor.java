package com.example.demo.patients;

import com.example.demo.generic.Convertor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class PatientConvertor extends Convertor<PatientDTO,PatientEntity> {
    public PatientConvertor() {
        super(patientDTO -> new PatientEntity(),patient -> new PatientDTO(
            patient.getId(),
            patient.getFullname(),
                patient.getBirthdate(),
                patient.getMobile(),
                patient.getGender(),
                patient.isDeleted(),
                patient.getRegion(),
                patient.getDestrict(),
                patient.getArea(),
                patient.getPassport()
        ));
    }


}
