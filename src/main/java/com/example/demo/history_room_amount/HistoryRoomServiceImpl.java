package com.example.demo.history_room_amount;

import com.example.demo.company.CompanyEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HistoryRoomServiceImpl implements HistoryRoomService {
   private final HistoryRoomRepository historyRoomRepository;
   private final HistoryRoomMapper historyRoomMapper;

    public HistoryRoomServiceImpl(HistoryRoomRepository historyRoomRepository, HistoryRoomMapper historyRoomMapper) {
        this.historyRoomRepository = historyRoomRepository;
        this.historyRoomMapper = historyRoomMapper;
    }

    @Override
    public HistoryRoomEntity findByWorkerId(UUID id) {
        return historyRoomRepository.getById(id);
    }

    @Override
    public HistoryRoomEntity save(CompanyEntity company, HistoryRoomEntity obj) {
        obj.setCompany(company);
        return historyRoomRepository.save(obj);
    }

    @Override
    public List<HistoryRoomDto> findByRoomIdAndCompanyId(UUID id,CompanyEntity company) {
    return historyRoomMapper.fromPageEntity(historyRoomRepository.findAllByRoomAndCompanyId(id,company.getId()));
    }

    @Override
    public void saveAll(List<HistoryRoomEntity> historyAnalyseList) {
         historyRoomRepository.saveAll(historyAnalyseList);
    }
}
