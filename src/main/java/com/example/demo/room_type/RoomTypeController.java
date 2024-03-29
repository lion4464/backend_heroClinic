package com.example.demo.room_type;

import com.example.demo.configuration.SwaggerUI;
import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.exceptions.DataNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
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
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<RoomTypeDTO> save(@RequestBody RoomTypeDTO request) throws DataNotFoundException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(roomTypeMapper.toDto(roomTypeService.save(request,userDetails.getUserEntity())), HttpStatus.OK);
    }
    @GetMapping("/all")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<RoomTypeDTO>> all(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(roomTypeMapper.fromPageEntity(roomTypeService.all(userDetails.getUserEntity())));
    }

    @GetMapping("/get_isset_room_types")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<Map<String,RoomTypeDTO>> getIssetRoomTypes(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(roomTypeMapper.fromIssetRoomTypes(roomTypeService.getIssetRoomTypes(userDetails.getUserEntity())));
    }
    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<RoomTypeDTO> update(@Valid @RequestBody RoomTypeDTO obj) throws DataNotFoundException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(roomTypeMapper.toDto(roomTypeService.update(obj,userDetails.getUserEntity())));
    }
    @GetMapping("get/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<RoomTypeDTO> get(@PathVariable("id") UUID id) throws DataNotFoundException {
        return ResponseEntity.ok().body(roomTypeMapper.toDto(roomTypeService.get(id)));
    }
    @DeleteMapping("/delete/{id}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(roomTypeService.delete(id));
    }
}
