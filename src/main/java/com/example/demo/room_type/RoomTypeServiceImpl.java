package com.example.demo.room_type;


import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class RoomTypeServiceImpl implements RoomTypeService{
    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public RoomTypeEntity save(RoomTypeDTO request, UserEntity user) {
        return readyEntity(request,user);
    }

    @Override
    public RoomTypeEntity get(UUID id) throws DataNotFoundException {
        Optional<RoomTypeEntity> optional = roomTypeRepository.findByIdAndDeleted(id,false);
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
    public RoomTypeEntity update(RoomTypeDTO obj,UserEntity user) {

        return readyEntity(obj,user);
    }
    private RoomTypeEntity readyEntity(RoomTypeDTO obj,UserEntity user){

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
