package com.example.demo.room;

import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.generic.DataStatusEnum;
import com.example.demo.history_room_amount.HistoryRoomDto;
import com.example.demo.history_room_amount.HistoryRoomService;
import com.example.demo.history_worker_salary.HistoryWorkerSalaryDto;

import com.example.demo.room_place.RoomPlaceResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/room")
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REGISTRATION')")
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;
    private final HistoryRoomService historyRoomService;

    public RoomController(RoomService roomService, RoomMapper roomMapper, HistoryRoomService historyRoomService) {
        this.roomService = roomService;
        this.roomMapper = roomMapper;
        this.historyRoomService = historyRoomService;
    }

    @PostMapping("/save")
    public ResponseEntity<RoomResponse> save(@Valid @RequestBody RoomRequest request){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(roomMapper.toDto(roomService.save(userDetails.getUserEntity(),request)), HttpStatus.OK);
    }

    @GetMapping("/get_status_inactive")
    public ResponseEntity<List<RoomResponse>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(roomMapper.fromPageEntity(roomService.all(userDetails.getUserEntity(),DataStatusEnum.INACTIVE)));
    }
    @GetMapping("/get_status_active")
    public ResponseEntity<List<RoomResponse>> getallStatusActive(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(roomMapper.fromPageEntity(roomService.all(userDetails.getUserEntity(),DataStatusEnum.ACTIVE)));
    }
    @PutMapping("/update")
    public ResponseEntity<RoomResponse> update(@Valid @RequestBody RoomRequest obj){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(roomMapper.toDto(roomService.update(obj,userDetails.getUserEntity())));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoomResponse> get(@PathVariable("id") UUID id){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(roomMapper.toDto(roomService.get(userDetails.getUserEntity(),id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(roomService.delete(id));
    }
    @GetMapping("/get_by_room_type/{roomTypeId}")
    public ResponseEntity<List<RoomResponse>> getByRoomTypeId(@PathVariable("roomTypeId") UUID roomTypeId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(roomMapper.fromPageEntity(roomService.getByRoomTypeId(userDetails.getUserEntity(),roomTypeId)));
    }

    @GetMapping("/about/{id}")
    public ResponseEntity<List<HistoryRoomDto>> getAbout(@PathVariable("id") UUID id){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(historyRoomService.findByRoomIdAndCompanyId(id,userDetails.getUserEntity().getCompany()));
    }


}
