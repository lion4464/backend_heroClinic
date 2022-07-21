package com.example.demo.room;

import com.example.demo.company.CompanyEntity;
import com.example.demo.company.CompanyService;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.NonUniqueResultException;
import com.example.demo.generic.DataStatusEnum;
import com.example.demo.history_room_amount.HistoryRoomEntity;
import com.example.demo.history_room_amount.HistoryRoomService;
import com.example.demo.room_place.RoomPlaceEntity;
import com.example.demo.room_place.RoomPlaceService;
import com.example.demo.room_type.RoomTypeEntity;
import com.example.demo.room_type.RoomTypeMapper;
import com.example.demo.room_type.RoomTypeService;
import com.example.demo.salary_type.SalaryTypeService;
import com.example.demo.user.UserEntity;
import com.example.demo.workers.WorkerService;
import com.example.demo.workers.WorkersEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.*;

@Service
public class RoomServiceImpl implements RoomService{


    private final RoomRepository roomRepository;
    private final HistoryRoomService historyRoomService;
    private final RoomPlaceService roomPlaceService;
    private final WorkerService workerService;
    private final RoomTypeService roomTypeService;
    private final RoomTypeMapper roomTypeMapper;
    private final SalaryTypeService salaryTypeService;
    private static final Logger logger = LogManager.getLogger();

    public RoomServiceImpl(RoomRepository roomRepository, HistoryRoomService historyRoomService, RoomPlaceService roomPlaceService, WorkerService workerService, RoomTypeService roomTypeService,  RoomTypeMapper roomTypeMapper, SalaryTypeService salaryTypeService) {
        this.roomRepository = roomRepository;
        this.historyRoomService = historyRoomService;
        this.roomPlaceService = roomPlaceService;
        this.workerService = workerService;

        this.roomTypeService = roomTypeService;
        this.roomTypeMapper = roomTypeMapper;
        this.salaryTypeService = salaryTypeService;
    }

    @Override
    public RoomEntity save(UserEntity user, RoomRequest request) {
        RoomEntity result = getReadyEntity(user,request);
        return result;
    }


    @Override
    public RoomEntity get(UserEntity user,UUID id) throws DataNotFoundException {
        Optional<RoomEntity> res = roomRepository.findByIdAndCompanyId(id,user.getCompanyId());
        if (res.isEmpty())
            throw new DataNotFoundException("Room is not found :(");
        res.get().setFreePlace(roomPlaceService.getAllPlaceFree(id,user.getCompanyId()).size());
        return res.get();
    }

    @Override
    public String delete(UUID id) {
        roomRepository.deleteById(id);
        return "Successfully removed";

    }

    @Override
    public List<RoomEntity> all(UserEntity user,DataStatusEnum status) {
       List<RoomEntity> roomEntityList = roomRepository.findAllByStatusAndCompanyId(status,user.getCompanyId());
        roomEntityList.forEach(roomEntity->{roomEntity.setFreePlace(get(user,roomEntity.getId()).getFreePlace());});
        return roomEntityList;
    }


    @Override
    public RoomEntity update(RoomRequest obj,UserEntity user) {
        RoomEntity entity = get(user,obj.getId());
        RoomTypeEntity roomType = roomTypeService.get(obj.getRoomTypeId());

            if (!obj.getStatus().equals(DataStatusEnum.INACTIVE))
                    return getReadyEntityForUpdate(obj,roomType,entity);
            else
                return afterChange(obj,user);

    }

    @Override
    public void changedSumIfRoomTypeSumChanged(UUID id, UUID roomType,UUID workerId,UserEntity user) {
        update(new RoomRequest(id,roomType,DataStatusEnum.ACTIVE,workerId),user);
    }

    @Override
    public List<RoomEntity> getByRoomTypeId(UserEntity user, UUID roomTypeId) {
        List<RoomEntity> roomEntityList = roomRepository.findAllByCompanyIdAndRoomTypeId(user.getCompanyId(),roomTypeId);
        roomEntityList.forEach(roomEntity->{roomEntity.setFreePlace(get(user,roomEntity.getId()).getFreePlace());});
        return roomEntityList;
    }

    private RoomEntity getReadyEntity(UserEntity user,RoomRequest request) {
        if (roomRepository.existsRoomEntitiesByNameAndStatus(request.getName(),DataStatusEnum.ACTIVE))
            throw new NonUniqueResultException("name is unique :)");

        RoomTypeEntity roomType = roomTypeService.get(request.getRoomTypeId());
        RoomEntity entity = new RoomEntity();
        entity.setName(request.getName());
        entity.setRoomType(roomType);
        entity.setCapacity(request.getCapacity());
            if ((request.getProtcent().equals(0))) {
                if (salaryTypeService.get(workerService.get(request.getWorkerId()).getSalaryTypeId()).getName().equals("PROTCENT"))
                    entity.setProtcent(request.getProtcent());
                else
                    entity.setProtcent(0.0F);
            }
            else{
                entity.setProtcent(request.getProtcent());
            }
        entity.setCompany(user.getCompany());
        entity.setPayAmount(roomType.getPaymentAmount());
        roomRepository.save(entity);
        List<RoomPlaceEntity> roomPlaceEntityList = new ArrayList<>();;
        if (request.getCapacity()>0) {
            WorkersEntity workers=workerService.get(request.getWorkerId());
            for (int i = 1; i <= request.getCapacity(); i++) {
                RoomPlaceEntity roomPlaceEntity = new RoomPlaceEntity();
                roomPlaceEntity.setProtcent(entity.getProtcent());
                roomPlaceEntity.setPlaceNumber(i);
                roomPlaceEntity.setClosedDate(request.getClosedDate());
                roomPlaceEntity.setPayAmount(roomType.getPaymentAmount());
                roomPlaceEntity.setWorkers(workers);
                roomPlaceEntity.setRoom(entity);
                roomPlaceEntity.setCompany(user.getCompany());
                roomPlaceEntity.setRoomType(roomType);
                roomPlaceEntityList.add(roomPlaceEntity);
            }
            HistoryRoomEntity historyRoomEntity = new HistoryRoomEntity();
            historyRoomEntity.setRoom(entity);
            historyRoomEntity.setAmount(roomType.getPaymentAmount());
            logger.info("Saving new place of Room and Theirs' history {} to db", request.getName());
            roomPlaceService.saveAll(roomPlaceEntityList);
            historyRoomService.save(user.getCompany(),historyRoomEntity);
        }
        return entity;
    }
       private RoomEntity getReadyEntityForUpdate(RoomRequest request,RoomTypeEntity roomType,RoomEntity entity){

               entity.setName(request.getName());
           List<RoomPlaceEntity> allplacesInRoom = new ArrayList<>();
                 if (Float.compare(roomType.getPaymentAmount(),entity.getPayAmount()) != 0){
                     entity.setPayAmount(roomType.getPaymentAmount());
                     logger.info("Update room {} to db",request.getName());
                     HistoryRoomEntity historyRoomEntity = new HistoryRoomEntity();
                     historyRoomEntity.setRoom(entity);
                     historyRoomEntity.setAmount(roomType.getPaymentAmount());
                     historyRoomService.save(entity.getCompany(),historyRoomEntity);
                     allplacesInRoom = roomPlaceService.getAllPlaces(entity.getCompany(),entity.getId());
                     for (RoomPlaceEntity roomPlace : allplacesInRoom) {
                         roomPlace.setPayAmount(roomType.getPaymentAmount());
                         roomPlace.setRoomType(roomType);
                         roomPlace.setStatus(request.getStatus());
                     }
                 }
                 entity.setStatus(request.getStatus());
           roomPlaceService.saveAll(allplacesInRoom);
           roomRepository.save(entity);
           return entity;
    }
    private RoomEntity afterChange(RoomRequest request,UserEntity user){
        RoomEntity entity = get(user,request.getId());
        entity.setStatus(request.getStatus());
       return roomRepository.save(entity);
    }


}
