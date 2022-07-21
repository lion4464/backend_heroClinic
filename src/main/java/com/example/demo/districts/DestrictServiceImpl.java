package com.example.demo.districts;

import com.example.demo.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestrictServiceImpl implements DestrictService{
    private final DestrictRepository destrictRepository;

    public DestrictServiceImpl(DestrictRepository destrictRepository) {
        this.destrictRepository = destrictRepository;
        }

    @Override
    public DestrictEntity get(Long id) {
        Optional<DestrictEntity> entity = destrictRepository.findById(id);
        if (entity.isEmpty())
            throw new DataNotFoundException("Destrict was not found");
    return entity.get();
    }

    @Override
    public DestrictEntity update(DestrictDTO request) {
        DestrictEntity entity = get(request.getId());
        entity.setNameOz(request.getNameOz());
        entity.setNameRu(request.getNameRu());
        return destrictRepository.save(entity);
    }

    @Override
    public List<DestrictEntity> all() {
        return destrictRepository.findAll();
    }
}
