package com.example.demo.company;

import com.example.demo.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyEntity save(CompanyRequest request) {
        CompanyEntity entity = new CompanyEntity();
        entity.setName(request.getName());
        return companyRepository.save(entity);
    }

    @Override
    public CompanyEntity get(UUID id) {
        Optional<CompanyEntity> companyRes = companyRepository.findById(id);
        if (companyRes.isEmpty())
            throw new DataNotFoundException("Company is not found :(");
        return companyRes.get();
    }

    @Override
    public CompanyEntity update(CompanyRequest request) {
        Optional<CompanyEntity> companyRes = companyRepository.findById(request.getId());
        if (companyRes.isEmpty())
            throw new DataNotFoundException("Company is not found :(");
        CompanyEntity companyEntity = companyRes.get();
        companyEntity.setName(request.getName());
        return companyEntity;
    }

    @Override
    public String delete(UUID id) {
        companyRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public List<CompanyEntity> all() {
       return companyRepository.findAll();
    }


}
