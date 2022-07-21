package com.example.demo.history_room_amount;

import com.example.demo.company.CompanyEntity;
import com.example.demo.history_worker_salary.HistoryWorkerSalaryDto;

import java.util.List;
import java.util.UUID;

public interface HistoryRoomService {
    HistoryRoomEntity findByWorkerId(UUID id);
    HistoryRoomEntity save(CompanyEntity company, HistoryRoomEntity obj);
    List<HistoryRoomDto> findByRoomIdAndCompanyId(UUID id,CompanyEntity company);
    void saveAll(List<HistoryRoomEntity> historyAnalyseList);
}
