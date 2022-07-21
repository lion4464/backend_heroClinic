package com.example.demo.region;

import com.example.demo.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService{
    private final com.example.demo.region.RegionRepository RegionRepository;

    public RegionServiceImpl(com.example.demo.region.RegionRepository RegionRepository) {
        this.RegionRepository = RegionRepository;
        }

    @Override
    public RegionEntity get(Long id) {
        Optional<RegionEntity> entity = RegionRepository.findById(id);
        if (entity.isEmpty())
            throw new DataNotFoundException("Region was not found");
    return entity.get();
    }

    @Override
    public RegionEntity update(RegionDTO request) {
        RegionEntity entity = get(request.getId());
        entity.setNameOz(request.getNameOz());
        entity.setNameRu(request.getNameRu());
        return RegionRepository.save(entity);
    }

    @Override
    public List<RegionEntity> all() {
        return RegionRepository.findAll();
    }
}
