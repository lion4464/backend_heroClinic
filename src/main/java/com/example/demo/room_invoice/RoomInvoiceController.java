package com.example.demo.room_invoice;

import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.generic.PageableRequest;
import com.example.demo.generic.UUIDSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/room_invoice")
@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REGISTRATION')")
public class RoomInvoiceController {
 private final RoomInvoiceService roomInvoiceService;
 private final RoomInvoiceMapper roomInvoiceMapper;
 private final RoomInvoiceConvertor roomInvoiceConvertor;

 public RoomInvoiceController(RoomInvoiceService roomInvoiceService, RoomInvoiceMapper roomInvoiceMapper, RoomInvoiceConvertor roomInvoiceConvertor) {
        this.roomInvoiceService = roomInvoiceService;
        this.roomInvoiceMapper = roomInvoiceMapper;
        this.roomInvoiceConvertor = roomInvoiceConvertor;
    }
    @PostMapping("/save")
    public ResponseEntity<RoomInvoiceResponse> save(@Valid @RequestBody RoomInvoiceRequest request){
        return new ResponseEntity<>(roomInvoiceMapper.toDto(roomInvoiceService.save(request)), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<RoomInvoiceResponse> update(@Valid @RequestBody RoomInvoiceRequest obj) throws IllegalArgumentException{
        return ResponseEntity.ok().body(roomInvoiceMapper.toDto(roomInvoiceService.update(obj)));
    }
    @GetMapping("get/{id}")
    public ResponseEntity<RoomInvoiceResponse> get(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(roomInvoiceMapper.toDto(roomInvoiceService.get(id)));
    }
    @PostMapping("/pageable")
    public ResponseEntity<Page<RoomInvoiceResponse>> pageable(@RequestBody PageableRequest pageable){
        PageRequest pageRequest = PageRequest.of(
                pageable.getPage(),
                pageable.getPerPage(),
                Sort.Direction.fromString(pageable.getSort().getDirection()),
                pageable.getSort().getName()
        );
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(roomInvoiceConvertor.createFromEntities(roomInvoiceService.all(
                new SearchSpecification(pageable.getSearch())
                        .and(new UUIDSpecification<>("companyId",userDetails.getUserEntity().getCompanyId())), pageRequest)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(roomInvoiceService.delete(id));
    }   
}
