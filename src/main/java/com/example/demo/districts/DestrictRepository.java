package com.example.demo.districts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestrictRepository extends JpaRepository<DestrictEntity,Long> {

}
