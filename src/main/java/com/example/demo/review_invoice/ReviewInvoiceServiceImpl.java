package com.example.demo.review_invoice;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewInvoiceServiceImpl implements ReviewInvoiceService {

    private final ReviewInvoiceRepository reviewInvoiceRepository;
    private final DepartmentService departmentService;
    private final WorkerService workerService;
    private final PatientService patientService;
    private final AnalysesService analysesService;
    private final WorkerMapper workerMapper;

    public ReviewInvoiceServiceImpl(ReviewInvoiceRepository reviewInvoiceRepository, DepartmentService departmentService, WorkerService workerService, PatientService patientService, AnalysesService analysesService, WorkerMapper workerMapper) {
        this.reviewInvoiceRepository = reviewInvoiceRepository;
        this.departmentService = departmentService;
        this.workerService = workerService;
        this.patientService = patientService;
        this.analysesService = analysesService;
        this.workerMapper = workerMapper;
    }

    @Override
    public ReviewInvoiceEntity update(ReviewInvoiceRequest obj) throws DataNotFoundException, StatusInactiveException {
        WorkersEntity worker = workerService.findById(obj.getWorkerId());
        if (worker.getStatus().equals(DataStatusEnum.INACTIVE))
            throw new StatusInactiveException("Worker status is INACTIVE");
        Optional<ReviewInvoiceEntity> optional = reviewInvoiceRepository.findById(obj.getId());
        if (optional.isEmpty())
            throw new DataNotFoundException("Review Invoice can't find");

        ReviewInvoiceEntity entity = optional.get();
        DepartmentEntity department = departmentService.get(obj.getDepartmentId());
        PatientEntity patient = patientService.findById(obj.getPatientId());


        entity.setWorker(worker);
        entity.setPatient(patient);
        entity.setDepartment(department);

        entity.setPaymentType(obj.getPaymentType());
        if (obj.getDiscount() > 0) {
            Float paidAmunt = getAmountAfterDiscount(worker.getReviewAmount(), obj.getDiscount());
            entity.setPaidAmount(paidAmunt);
        } else {
            entity.setPaidAmount(worker.getReviewAmount());
        }
        reviewInvoiceRepository.save(entity);
        return entity;
    }

    @Override
    public String delete(UUID id) {
        reviewInvoiceRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public ReviewInvoiceEntity getId(UUID id) throws DataNotFoundException {
        Optional<ReviewInvoiceEntity> optional = reviewInvoiceRepository.findById(id);
        if (optional.isEmpty())
            throw new DataNotFoundException("Review invoice can't found :/");
        return optional.get();
    }

    @Override
    public ReviewInvoiceEntity save(ReviewInvoiceRequest obj, UserEntity user) throws DataNotFoundException, StatusInactiveException {
        WorkersEntity worker = workerService.findById(obj.getWorkerId());
        if (worker.getStatus().equals(DataStatusEnum.INACTIVE))
            throw new StatusInactiveException("Worker status is INACTIVE");

        ReviewInvoiceEntity entity = new ReviewInvoiceEntity();
        DepartmentEntity department = departmentService.get(obj.getDepartmentId());
        PatientEntity patient = patientService.findById(obj.getPatientId());

        entity.setCompany(user.getCompany());
        entity.setWorker(worker);
        entity.setPatient(patient);
        entity.setDepartment(department);
        entity.setPaymentType(obj.getPaymentType());
        if (obj.getDiscount() > 0) {
            Float paidAmunt = getAmountAfterDiscount(worker.getReviewAmount(), obj.getDiscount());
            entity.setPaidAmount(paidAmunt);
        } else {
            entity.setPaidAmount(worker.getReviewAmount());
        }
        reviewInvoiceRepository.save(entity);
        return entity;
    }


    @Override
    public Page<ReviewInvoiceEntity> all(Specification<ReviewInvoiceEntity> spec, Pageable page) {
        return reviewInvoiceRepository.findAll(spec, page);
    }

    @Override
    public List<ReviewInvoiceCount> getReviewsCount(Date from, Date to,UserEntity user) throws DataNotFoundException {
        List<IReviewInvoiceCount> reviewInvoiceCounts = reviewInvoiceRepository.getReviewsCount(from.getTime(),
                to.getTime()+86399999,user.getCompanyId());

        List<ReviewInvoiceCount> reviewCounts = new ArrayList<>();
        for (IReviewInvoiceCount reviewInvoiceCount : reviewInvoiceCounts) {
            reviewCounts.add(new ReviewInvoiceCount(workerMapper.toDto(workerService.findById(reviewInvoiceCount.getWorkerId())),
                    reviewInvoiceCount.getCount()));
        }
        return reviewCounts;
    }


    private Float getAmountAfterDiscount(Float reviewAmount, Integer discount) {
        return ((100 - discount) * reviewAmount) / 100;
    }
}
