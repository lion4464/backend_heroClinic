package com.example.demo.room;

import com.example.demo.exceptions.NonUniqueResultException;
import com.example.demo.generic.DataStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, UUID> {


    boolean existsRoomEntitiesByNameAndStatus(String name,DataStatusEnum dataStatusEnum) throws NonUniqueResultException;



    List<RoomEntity> findAllByStatusAndCompanyId(DataStatusEnum status,UUID companyId);


    Optional<RoomEntity> findByIdAndCompanyId(UUID id, UUID companyId);

    List<RoomEntity> findAllByCompanyIdAndRoomTypeId(UUID companyId, UUID roomId);
}
