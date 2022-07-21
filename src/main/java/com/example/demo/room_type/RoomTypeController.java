package com.example.demo.room_type;

import com.example.demo.configuration.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_USER')")
@RequestMapping("/api/room_type")
public class RoomTypeController {
    private final RoomTypeService roomTypeService;
    private final RoomTypeMapper roomTypeMapper;

    public RoomTypeController(RoomTypeService roomTypeService, RoomTypeMapper roomTypeMapper) {
        this.roomTypeService = roomTypeService;
        this.roomTypeMapper = roomTypeMapper;
    }
    @PostMapping("/save")
    public ResponseEntity<RoomTypeDTO> save(@RequestBody RoomTypeDTO request){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(roomTypeMapper.toDto(roomTypeService.save(request,userDetails.getUserEntity())), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<RoomTypeDTO>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(roomTypeMapper.fromPageEntity(roomTypeService.all(userDetails.getUserEntity())));
    }
    @PutMapping("/update")
    public ResponseEntity<RoomTypeDTO> update(@Valid @RequestBody RoomTypeDTO obj){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(roomTypeMapper.toDto(roomTypeService.update(obj,userDetails.getUserEntity())));
    }
    @GetMapping("get/{id}")
    public ResponseEntity<RoomTypeDTO> get(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(roomTypeMapper.toDto(roomTypeService.get(id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(roomTypeService.delete(id));
    }
}
