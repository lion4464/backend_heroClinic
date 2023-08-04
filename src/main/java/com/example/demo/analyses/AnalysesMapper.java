package com.example.demo.analyses;

import com.example.demo.generic.GenericAuditMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnalysesMapper extends GenericAuditMapper<AnalysesEntity,AnalysesResponse> {

}
