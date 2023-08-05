package com.example.demo.patients;

import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.DataNotFoundException;

import com.example.demo.generic.JpaGenericRepository;
import com.example.demo.generic.JpaGenericServiceImpl;
import com.example.demo.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PatientServiceImpl   extends JpaGenericServiceImpl<PatientEntity, UUID> implements PatientService{

    private final PatientRepo patientRepo;

    public PatientServiceImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public PatientEntity getId(UUID id) throws DataNotFoundException {
        Optional<PatientEntity> entity=patientRepo.findById(id);
        if (entity.isEmpty())
            throw new DataNotFoundException("Patient isn't not found Patient");
        return patientRepo.getById(id);
    }
    public PatientEntity insert(UserEntity user, PatientEntity obj){
        obj.setCompany(user.getCompany());
        return patientRepo.save(obj);
    }

    public PatientEntity update(PatientEntity obj) throws DataNotFoundException {
        findById(obj.getId());
        return patientRepo.save(obj);
    }


    public Page<PatientEntity> all(Specification<PatientEntity> spec,Pageable page){
       return patientRepo.findAll(spec,
               page);
    }


    @Override
    protected JpaGenericRepository<PatientEntity, UUID> getRepository() {
        return patientRepo;
    }
}
