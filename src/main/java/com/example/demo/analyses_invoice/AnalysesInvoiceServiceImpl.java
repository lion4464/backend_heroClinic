package com.example.demo.analyses_invoice;

import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.analyses.AnalysesService;
import com.example.demo.department.DepartmentEntity;
import com.example.demo.department.DepartmentService;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.StatusInactiveException;
import com.example.demo.generic.DataStatusEnum;
import com.example.demo.patients.PatientEntity;
import com.example.demo.patients.PatientService;
import com.example.demo.user.UserEntity;
import com.example.demo.workers.WorkerMapper;
import com.example.demo.workers.WorkerService;
import com.example.demo.workers.WorkersEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;
@Service
public class AnalysesInvoiceServiceImpl implements AnalysesInvoiceService{
    private final DepartmentService departmentService;
    private final WorkerService workerService;
    private final PatientService patientService;
    private final AnalysesService analysesService;
    private final AnalysesInvoiceRepository analysesInvoiceRepository;
    private final WorkerMapper workerMapper;
    private static final Logger logger = LogManager.getLogger();

    public AnalysesInvoiceServiceImpl(DepartmentService departmentService, WorkerService workerService, PatientService patientService, AnalysesService analysesService, AnalysesInvoiceRepository analysesInvoiceRepository, WorkerMapper workerMapper) {
        this.departmentService = departmentService;
        this.workerService = workerService;
        this.patientService = patientService;
        this.analysesService = analysesService;
        this.analysesInvoiceRepository = analysesInvoiceRepository;
        this.workerMapper = workerMapper;
    }

    @Override
    public AnalysesInvoiceEntity update(AnalysesInvoiceRequest obj) {
        get(obj.getId());
        return analysesInvoiceRepository.save(getReadyEntity(obj));
    }

    @Override
    public String delete(UUID id) {
        analysesInvoiceRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public AnalysesInvoiceEntity get(UUID id) {
        Optional<AnalysesInvoiceEntity> optional = analysesInvoiceRepository.findById(id);
        if (optional.isEmpty())
            throw new DataNotFoundException("Analyse invoice invoice can't found :/");
        return optional.get();
    }

    @Override
    public AnalysesInvoiceEntity save(AnalysesInvoiceRequest obj) {
        AnalysesInvoiceEntity entity = getReadyEntity(obj);
         analysesInvoiceRepository.save(entity);
        logger.info("New  Analyse Invice created ))");
        return entity;

    }

    @Override
    public List<AnalysesInvoiceEntity> saveAll(List<AnalysesInvoiceRequest> objList) {

        List<AnalysesInvoiceEntity> entityList = objList.stream().map(analysesInvoiceRequest -> getReadyEntity(analysesInvoiceRequest)).collect(Collectors.toList());

        analysesInvoiceRepository.saveAll(entityList);
        return entityList;
    }



    @Override
    public Page<AnalysesInvoiceEntity> all(Specification<AnalysesInvoiceEntity> spec, Pageable page) {
        return analysesInvoiceRepository.findAll(spec,
                page);
    }


    private AnalysesInvoiceEntity getReadyEntity(AnalysesInvoiceRequest obj){
        AnalysesEntity analyses = analysesService.get(obj.getAnalyseId());
        WorkersEntity worker =workerService.get(analyses.getWorkerId());
        if (worker.getStatus().equals(DataStatusEnum.INACTIVE))
            throw new StatusInactiveException("Worker status is INACTIVE");
        DepartmentEntity department = departmentService.get(worker.getDepartmentId());
        PatientEntity patient = patientService.getId(obj.getPatientId());

        AnalysesInvoiceEntity entity;
        if (obj.getId()==null)
            entity = new AnalysesInvoiceEntity();
        else{
            Optional<AnalysesInvoiceEntity> optional = analysesInvoiceRepository.findById(obj.getId());
            if (optional.isEmpty())
                throw new DataNotFoundException("Analyse Invoice is not found this id");
            else
                entity=optional.get();
        }

        entity.setCompany(analyses.getCompany());
        entity.setWorker(worker);
        entity.setPatient(patient);
        entity.setDepartment(department);
        entity.setAnalyse(analyses);
        entity.setPaymentType(obj.getPaymentType());
        if (obj.getDiscount()>0){
            Float paidAmunt = getAmountAfterDiscount(entity.getAnalyse().getPayAmount(),obj.getDiscount());
            entity.setPaidAmount(paidAmunt);
            entity.setDiscount(obj.getDiscount());
        }else {
            entity.setPaidAmount(entity.getAnalyse().getPayAmount());
        }
        entity.setTotalAmount(entity.getAnalyse().getPayAmount());
        return entity;
    }

    private Float getAmountAfterDiscount(Float reviewAmount, Integer discount) {

        return ((100-discount)*Float.valueOf(reviewAmount))/100;
    }

    //berilgan vaqtlar oralig'idagi
    @Override
    public List<AnalyseInvoiseCounterDto> getCountInvoicesBetweenDates(UserEntity user,Date time1, Date time2) {
        List<AnalyseInvoiseCounterDto> counts2=new ArrayList<>();
        List<IAnalyseInvoiceCount> counts=analysesInvoiceRepository.groupByInvoicesBetweenTwoDateWhereCompanyId(user.getCompanyId(),time1.getTime(),time2.getTime()+86399999);
       for (int i=0;i<counts.size();i++)
       {
           counts2.add(new AnalyseInvoiseCounterDto(counts.get(i).getCounts(),workerMapper.toDto(workerService.get(counts.get(i).getWorkerId()))));
       }
        return counts2;

    }
}
