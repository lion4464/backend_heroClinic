package com.example.demo.salary_type;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SalaryTypeRepository extends JpaRepository<SalaryTypeEntity, UUID> {
}
