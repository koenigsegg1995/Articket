package com.maddog.articket.seatstatus.service.impl;

import com.maddog.articket.seatstatus.dao.SeatStatusRepository;
import com.maddog.articket.seatstatus.entity.SeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatStatusService {
    @Autowired
    private SeatStatusRepository seatStatusRepository;

    public SeatStatus getSeatStatusByActivityTimeSlotIdAndSeatId(Integer activityTimeSlotId, Integer seatId) {
        return seatStatusRepository.findSeatStatusByActivityTimeSlotIdAndSeatId(activityTimeSlotId, seatId);
    }

    public List<SeatStatus> getAllSeatStatusByActivityTimeSlotID(Integer activityTimeSlotID) {
        return seatStatusRepository.findAllByActivityTimeSlotID(activityTimeSlotID);
    }

    // 為了在seatSelectPage顯示不可選取的座位而寫的方法
    public List<String> getUnavailableSeatNames(Integer activityTimeSlotID) {
        // 假設狀態 2 和 3 表示座位不可用
        return seatStatusRepository.findUnavailableSeatNamesByActivityTimeSlotID(activityTimeSlotID, 2, 3);
    }

    public void updateSeatStatusToReserved(Integer seatID, Integer activityTimeSlotID) {
        updateSeatStatus(seatID, activityTimeSlotID, 3); // 將狀態設置為 3（保留）
    }

    // 新增：更新座位狀態的通用方法
    public void updateSeatStatus(Integer seatID, Integer activityTimeSlotID, Integer newStatus) {
        SeatStatus seatStatus = seatStatusRepository.findSeatStatusByActivityTimeSlotIdAndSeatId(activityTimeSlotID, seatID);
        if (seatStatus != null) {
            seatStatus.setSeatStatus(newStatus);
            seatStatusRepository.save(seatStatus);
        } else {
            throw new RuntimeException(
                "SeatStatus not found for seatID: " + seatID + " and activityTimeSlotID: " + activityTimeSlotID);
        }
    }

    // 獲取 seatStatus 為 2 的座位名稱
    public List<String> getSeatNamesWithStatus2(Integer activityTimeSlotID) {
        return seatStatusRepository.findSeatNamesWithStatus2(activityTimeSlotID);
    }

    // 獲取 seatStatus 為 3 的座位名稱
    public List<String> getSeatNamesWithStatus3(Integer activityTimeSlotID) {
        return seatStatusRepository.findSeatNamesWithStatus3(activityTimeSlotID);
    }

    // 新增：取消保留座位（將狀態設為1，表示可用）
    public void cancelReservation(Integer seatID, Integer activityTimeSlotID) {
        updateSeatStatus(seatID, activityTimeSlotID, 1); // 將狀態設置為 1（可用）
    }
}