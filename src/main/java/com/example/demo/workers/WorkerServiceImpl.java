package com.example.demo.workers;

import com.example.demo.department.DepartmentEntity;
import com.example.demo.department.DepartmentService;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.DataStatusEnum;
import com.example.demo.history_worker_salary.HistoryWorkerSalary;
import com.example.demo.history_worker_salary.HistoryWorkerSalaryService;
import com.example.demo.salary_type.SalaryTypeEntity;
import com.example.demo.salary_type.SalaryTypeService;
import com.example.demo.user.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkerServiceImpl implements WorkerService{

    private final WorkerRepository workerRepository;
    private final DepartmentService departmentService;
    private final SalaryTypeService salaryTypeService;
    private final HistoryWorkerSalaryService historyWorkerSalaryService;


    private static final Logger logger = LogManager.getLogger();

    public WorkerServiceImpl(WorkerRepository workerRepository, DepartmentService departmentService, SalaryTypeService salaryTypeService, HistoryWorkerSalaryService historyWorkerSalaryService) {
        this.workerRepository = workerRepository;
        this.departmentService = departmentService;
        this.salaryTypeService = salaryTypeService;
        this.historyWorkerSalaryService = historyWorkerSalaryService;
    }

    @Override
    public WorkersEntity save(WorkerRequest request) {
        logger.info("Saving new  worker {} to db",request.getFullname());
        WorkersEntity entity = new WorkersEntity();
        DepartmentEntity department = departmentService.get(request.getDepartmentId());
        SalaryTypeEntity salaryType = salaryTypeService.get(request.getSalaryTypeId());
        HistoryWorkerSalary historyWorkerSalary = new HistoryWorkerSalary();

        entity.setFullname(request.getFullname());
        entity.setMobile(request.getMobile());
        entity.setDestrict(request.getDestrict());
        entity.setRegion(request.getRegion());
        entity.setArea(request.getArea());
        entity.setGender(request.getGender());
        entity.setBirthdate(request.getBirthdate());
        entity.setStartedDate(request.getStartedDate());
        entity.setDepartment(department);
        entity.setSalaryType(salaryType);
        entity.setReviewAmount(request.getReviewAmount());
        entity.setPosition(request.getPosition());
        historyWorkerSalary.setReviewAmount(request.getReviewAmount());
        historyWorkerSalary.setAmount(request.getSalaryAmount());

        entity.setSalaryAmount(request.getSalaryAmount());
        entity.setKeyId(request.getKeyId());
        entity.setRoom(request.getRoom());
        WorkersEntity result = workerRepository.save(entity);

        historyWorkerSalary.setWorker(result);
        historyWorkerSalary.setStartedDate(request.getStartedDate());
        historyWorkerSalaryService.save(historyWorkerSalary);

    return result;
    }

    @Override
    public WorkersEntity get(UUID id) throws DataNotFoundException {
        Optional<WorkersEntity> optional = workerRepository.findById(id);
        if (optional.isEmpty())
            throw new DataNotFoundException("Worker not found:/");
        return optional.get();
    }

    @Override
    public String delete(UUID id) {

        workerRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public List<WorkersEntity> findAllInactiveStatus(UserEntity user) {
        return workerRepository.findAllByStatusAndCompanyId(DataStatusEnum.INACTIVE,user.getCompanyId());
    }

    @Override
    public List<WorkersEntity> findAllActiveStatus(UserEntity user) {
        return workerRepository.findAllByStatusAndCompanyId(DataStatusEnum.ACTIVE,user.getCompanyId());
    }

    @Override
    public WorkersEntity update(WorkerRequest request) {
        logger.info("Updating new  worker {} to db",request.getFullname());
        DepartmentEntity department = departmentService.get(request.getDepartmentId());
        SalaryTypeEntity salaryType = salaryTypeService.get(request.getSalaryTypeId());
        WorkersEntity entity = get(request.getId());
        entity.setFullname(request.getFullname());
        entity.setMobile(request.getMobile());
        entity.setDestrict(request.getDestrict());
        entity.setRegion(request.getRegion());
        entity.setArea(request.getArea());
        entity.setGender(request.getGender());
        entity.setBirthdate(request.getBirthdate());
        entity.setStartedDate(request.getStartedDate());
        entity.setDepartment(department);
        entity.setSalaryAmount(request.getSalaryAmount());
        entity.setPosition(request.getPosition());
        entity.setKeyId(request.getKeyId());
        entity.setRoom(request.getRoom());

        if (!entity.getSalaryAmount().equals(request.getSalaryAmount()) || (!entity.getReviewAmount().equals(request.getReviewAmount()))){
            HistoryWorkerSalary historyEntity = new HistoryWorkerSalary();
            historyEntity.setWorker(entity);
            historyEntity.setAmount(request.getSalaryAmount());
            historyEntity.setReviewAmount(request.getReviewAmount());
            historyWorkerSalaryService.save(historyEntity);
            entity.setSalaryType(salaryType);
            entity.setReviewAmount(request.getReviewAmount());
        }
       else if ((!request.getExpiredDate().equals(0))&&(entity.getStatus().equals(DataStatusEnum.ACTIVE))&&(request.getStatus().equals(DataStatusEnum.INACTIVE))){
            entity.setExpiredDate(getMillisecondNow());
            entity.setStatus(DataStatusEnum.INACTIVE);
            entity.setStartedDate(getMillisecondNow());
            HistoryWorkerSalary historyEntity = new HistoryWorkerSalary();
            historyEntity.setWorker(entity);
            historyEntity.setAmount(request.getSalaryAmount());
            historyEntity.setReviewAmount(request.getReviewAmount());
            historyEntity.setEndDate(getMillisecondNow());
            historyWorkerSalaryService.save(historyEntity);

        }
        else if (entity.getStatus().equals(DataStatusEnum.INACTIVE)&& request.getStatus().equals(DataStatusEnum.ACTIVE)){
            HistoryWorkerSalary historyEntity = new HistoryWorkerSalary();
            historyEntity.setWorker(entity);
            historyEntity.setAmount(request.getSalaryAmount());
            historyEntity.setReviewAmount(request.getReviewAmount());
            historyEntity.setStartedDate(request.getStartedDate());
            entity.setStatus(DataStatusEnum.ACTIVE);
            historyWorkerSalaryService.save(historyEntity);

            }

        return  workerRepository.save(entity);

    }
    public Long getMillisecondNow(){
        Date date = new Date();
        return date.getTime();
    }
}
