package com.example.demo.room_place;


import com.example.demo.configuration.SwaggerUI;
import com.example.demo.configuration.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/room_place")
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REGISTRATION')")
public class RoomPlaceController {

    private final RoomPlaceService roomPlaceService;

    private final RoomPlaceMapper roomPlaceMapper;

    public RoomPlaceController(RoomPlaceService roomPlaceService, RoomPlaceMapper roomPlaceMapper) {
        this.roomPlaceService = roomPlaceService;
        this.roomPlaceMapper = roomPlaceMapper;
    }

    @GetMapping("/get/{roomId}")
    @Operation(security = {@SecurityRequirement(name = SwaggerUI.AccessToken)},summary = "")
    public ResponseEntity<List<RoomPlaceResponce>> getAllFree(@PathVariable UUID roomId)
    {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(roomPlaceMapper.fromEntityList(roomPlaceService.getAllPlaces(userDetails.getUserEntity().getCompany(),roomId)));
    }
}
