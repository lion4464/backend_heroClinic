package com.example.demo.workers;


import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkerMapper {
    WorkerFullDTO toDto(WorkersEntity worker);
    WorkersEntity fromDto(WorkerRequest workerRequest);
    List<WorkerFullDTO> fromPageEntity(List<WorkersEntity>  entityList);
}
