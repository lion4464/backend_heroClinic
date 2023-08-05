package com.example.demo.room_type;


import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.DataStatusEnum;
import com.example.demo.room.RoomEntity;
import com.example.demo.room.RoomRepository;
import com.example.demo.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class RoomTypeServiceImpl implements RoomTypeService{

    private final RoomTypeRepository roomTypeRepository;
    private final RoomRepository roomRepository;

    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository, RoomRepository roomRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomTypeEntity save(RoomTypeDTO request, UserEntity user) throws DataNotFoundException {
        return readyEntity(request,user);
    }

    @Override
    public RoomTypeEntity get(UUID id) throws DataNotFoundException {
        Optional<RoomTypeEntity> optional = roomTypeRepository.findById(id);
        if (optional.isEmpty())
            throw new DataNotFoundException("Room Type is not found this id");
        return optional.get();
    }

    @Override
    public String delete(UUID id) {
        roomTypeRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public List<RoomTypeEntity> all(UserEntity user) {
        return roomTypeRepository.findAllByCompanyIdAndDeleted(user.getCompanyId(),false);
    }

    @Override
    public RoomTypeEntity update(RoomTypeDTO obj,UserEntity user) throws DataNotFoundException {

        return readyEntity(obj,user);
    }

    @Override
    public HashMap<String, RoomTypeEntity> getIssetRoomTypes(UserEntity userEntity) {
    List<RoomEntity> roomEntityList = roomRepository.findAllByStatusAndCompanyId(DataStatusEnum.ACTIVE,userEntity.getCompanyId());
    HashMap<String,RoomTypeEntity> resultMap = new HashMap<>();
    for (RoomEntity room : roomEntityList) {
            resultMap.put((room.getRoomType().getName()==null) ? "empty" : room.getRoomType().getName(),
                    room.getRoomType());
        }
        return resultMap;
    }

    private RoomTypeEntity readyEntity(RoomTypeDTO obj,UserEntity user) throws DataNotFoundException {

        RoomTypeEntity entity;
        if (obj.getId()==null)
            entity = new RoomTypeEntity();
        else{
           entity = get(obj.getId());
        }
        entity.setCompany(user.getCompany());
        entity.setName(obj.getName());
        entity.setPaymentAmount(obj.getPaymentAmount());
        roomTypeRepository.save(entity);
        return entity;
    }
}
