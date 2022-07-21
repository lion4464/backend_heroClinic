package com.example.demo.room_invoice;

import com.example.demo.cashier.CashierService;
import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.department.DepartmentEntity;
import com.example.demo.department.DepartmentService;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.StatusInactiveException;
import com.example.demo.generic.DataStatusEnum;
import com.example.demo.patient_dept.PatientDeptEntity;
import com.example.demo.patient_dept.PatientDeptRequest;
import com.example.demo.patient_dept.PatientDeptService;
import com.example.demo.patients.PatientEntity;
import com.example.demo.patients.PatientService;
import com.example.demo.room.RoomEntity;
import com.example.demo.room.RoomRequest;
import com.example.demo.room.RoomService;
import com.example.demo.room_place.RoomPlaceEntity;
import com.example.demo.room_place.RoomPlaceService;
import com.example.demo.room_type.RoomTypeEntity;
import com.example.demo.workers.WorkerService;
import com.example.demo.workers.WorkersEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class RoomInvoiceServiceImpl implements RoomInvoiceService {
    private final DepartmentService departmentService;
    private final WorkerService workerService;
    private final PatientService patientService;
    private final PatientDeptService patientDeptService;
    private final RoomService roomService;
    private final RoomPlaceService roomPlaceService;
    private final CashierService cashierService;
    private final RoomInvoiceRepository roomRepository;
    private static final Logger logger = LogManager.getLogger();

    public RoomInvoiceServiceImpl(DepartmentService departmentService, WorkerService workerService, PatientService patientService, PatientDeptService patientDeptService, RoomService roomService, RoomPlaceService roomPlaceService, CashierService cashierService, RoomInvoiceRepository roomRepository) {
        this.departmentService = departmentService;
        this.workerService = workerService;
        this.patientService = patientService;
        this.patientDeptService = patientDeptService;
        this.roomService = roomService;
        this.roomPlaceService = roomPlaceService;
        this.cashierService = cashierService;
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomInvoiceEntity update(RoomInvoiceRequest obj) throws IllegalArgumentException {
        get(obj.getId());
        return roomRepository.save(getReadyEntity(obj));
    }

    @Override
    public String delete(UUID id) {
        roomRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public RoomInvoiceEntity get(UUID id) {
        Optional<RoomInvoiceEntity> optional = roomRepository.findById(id);
        if (optional.isEmpty())
            throw new DataNotFoundException("Room invoice can't found :/");
        return optional.get();
    }

    @Override
    public RoomInvoiceEntity save(RoomInvoiceRequest obj) {
        RoomInvoiceEntity entity = getReadyEntity(obj);
         roomRepository.save(entity);
        logger.info("New  Room Invice created ))");
        return entity;

    }



    @Override
    public Page<RoomInvoiceEntity> all(Specification<RoomInvoiceEntity> spec, Pageable page) {
        return roomRepository.findAll(spec,
                page);
    }


    private RoomInvoiceEntity getReadyEntity(RoomInvoiceRequest obj){
         RoomPlaceEntity roomPlace = roomPlaceService.get(obj.getRoomPlaceId());
         RoomEntity room = roomPlace.getRoom();
        WorkersEntity worker = workerService.get(obj.getWorkerId());
        if (worker.getStatus().equals(DataStatusEnum.INACTIVE))
            throw new StatusInactiveException("Worker status is INACTIVE");

        DepartmentEntity department = departmentService.get(worker.getDepartmentId());
        PatientEntity patient = patientService.getId(obj.getPatientId());

        RoomInvoiceEntity entity;
        if (obj.getId()==null)
            entity = new RoomInvoiceEntity();
        else
            entity = get(obj.getId());
        entity.setCompany(room.getCompany());
        entity.setWorker(worker);
        entity.setPatient(patient);
        entity.setDepartment(department);
        entity.setRoomPlace(roomPlace);
        if (roomPlace.getProtcent()>0)
            entity.setProtcent(roomPlace.getProtcent());
        else
             entity.setProtcent(0.0F);
        entity.setPaymentType(obj.getPaymentType());
        PatientDeptEntity patientDept = null;
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
// set discount for patient if geting from request object;
        if (obj.getDiscount()>0){
//            If roomType sum is changed Sum need to update room

            roomService.changedSumIfRoomTypeSumChanged(room.getId(),room.getRoomType().getId(),roomPlace.getWorkerId(), userDetails.getUserEntity());
//      Payed Amount with  Discount;
            Float paidAmunt = getAmountAfterDiscount(obj.getDelay()*roomPlace.getPayAmount(),obj.getDiscount());
            entity.setPaidAmount(paidAmunt);
//            seve total payed amount as patient dept;
            Float totalDept = paidAmunt;
            if (entity.getPatientDept()==null)
            patientDept = patientDeptService.save(new PatientDeptRequest(null,totalDept,obj.getPatientId(),null));
            else {
                PatientDeptEntity changed_patient_dept = entity.getPatientDept();
                changed_patient_dept.setSum(totalDept);
                patientDept = patientDeptService.save(changed_patient_dept);
                }
            }

        else {

            roomService.changedSumIfRoomTypeSumChanged(room.getId(),room.getRoomType().getId(),roomPlace.getWorkerId(),userDetails.getUserEntity());
            Float totalDept = (obj.getDelay()*roomPlace.getPayAmount());
            entity.setPaidAmount(obj.getDelay()*roomPlace.getPayAmount());
              if (entity.getPatientDept()==null)
                  patientDept = patientDeptService.save(new PatientDeptRequest(null,totalDept,obj.getPatientId(),null));
              else{
                  PatientDeptEntity changed_patient_dept = entity.getPatientDept();
                  changed_patient_dept.setSum(totalDept);
                  patientDept = patientDeptService.save(changed_patient_dept);
              }
        }
        if (obj.getCashierId() != null)
            entity.setCashier(cashierService.get(obj.getCashierId()));
        entity.setCashier(null);

        if (!obj.getDelay().equals(0))
            roomPlaceService.updateCloseDate(roomPlace.getId(),createCloseDate(obj.getDelay()));
        entity.setCompany(room.getCompany());
        entity.setPatientDept(patientDept);
        return entity;
    }


    private Long createCloseDate(Integer delay) {
        return System.currentTimeMillis()+toMilliSeconds(delay);
    }
    public Long toMilliSeconds(Integer day)
    {
        return Long.valueOf(day * 24 * 60 * 60 * 1000);
    }

    private Float getAmountAfterDiscount(Float reviewAmount, Integer discount) {
        return ((100-discount)*reviewAmount)/100;
    }
}
