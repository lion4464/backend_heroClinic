package com.example.demo.department;

import com.example.demo.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, UUID> {
    List<DepartmentEntity> findAllByCompanyIdAndDeleted(UUID companyId,Boolean deleted);
}
