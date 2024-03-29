package com.example.demo.room_place;


import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.company.CompanyEntity;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.JpaGenericRepository;
import com.example.demo.generic.JpaGenericServiceImpl;
import com.example.demo.user.UserEntity;
import com.example.demo.workers.WorkerService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomPlaceServiceImpl   extends JpaGenericServiceImpl<RoomPlaceEntity, UUID> implements RoomPlaceService {


   private final RoomPlaceRepository roomPlaceRepository;

   private final WorkerService workerService;

    public RoomPlaceServiceImpl(RoomPlaceRepository roomPlaceRepository, WorkerService workerService) {
        this.roomPlaceRepository = roomPlaceRepository;
        this.workerService = workerService;
    }

    @Override
    public void saveAll(List<RoomPlaceEntity> roomPlaceEntity) {
        roomPlaceRepository.saveAll(roomPlaceEntity);
    }

    @Override
    public List<RoomPlaceEntity> getAllPlaces(CompanyEntity company, UUID roomId) {
        return roomPlaceRepository.getAllByRoomIdAndCompanyId(roomId,company.getId());
    }

    @Override
    public RoomPlaceEntity get(UUID id) throws DataNotFoundException {
        Optional<RoomPlaceEntity> roomPlace=roomPlaceRepository.findById(id);
        if (roomPlace.isEmpty())
            throw new DataNotFoundException("Room place Not found");
        return roomPlace.get();
    }


    @Override
    public List<RoomPlaceEntity> getAllPlaceFree(UUID roomId,UUID companyId) throws DataNotFoundException {
        List<RoomPlaceEntity> roomPlaceEntities=roomPlaceRepository.getAllByClosedDateAndRoomIdAndCompanyId(0L,roomId,companyId);
        for (int i=0;i<roomPlaceEntities.size();i++)
        {
            roomPlaceEntities.get(i).setWorker(workerService.findById(roomPlaceEntities.get(i).getWorkerId()));
        }
          return roomPlaceEntities;
    }

    @Override
    public List<RoomPlaceEntity> findAllById(UUID id) {
       return roomPlaceRepository.findAllById(Collections.singleton(id));
    }

    @Override
    public void updateCloseDate(UUID roomid, Long closeDate) throws DataNotFoundException {
        RoomPlaceEntity entity = get(roomid);
        entity.setClosedDate(closeDate);
        roomPlaceRepository.save(entity);
    }

    @Override
    protected JpaGenericRepository<RoomPlaceEntity, UUID> getRepository() {
        return roomPlaceRepository;
    }
}
