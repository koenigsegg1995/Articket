package com.maddog.articket.seatstatus.service.impl;

import com.maddog.articket.seatstatus.dao.SeatStatusDao;
import com.maddog.articket.seatstatus.entity.SeatStatus;
import com.maddog.articket.seatstatus.service.pri.SeatStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 座位狀態 Service Implementation
 */
@Service("seatStatusService")
public class SeatStatusServiceImpl implements SeatStatusService {

    /**
     * 座位狀態 DAO
     */
    @Autowired
    private SeatStatusDao seatStatusDao;

    /**
     * 依 活動時段 ID 和座位 ID 查詢
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @param seatId
     *          座位 ID
     * @return 座位狀態
     */
    @Override
    @Transactional(readOnly = true)
    public SeatStatus getSeatStatusByActivityTimeSlotIdAndSeatId(Integer activityTimeSlotId, Integer seatId) {
        return seatStatusDao.findSeatStatusByActivityTimeSlotIdAndSeatId(activityTimeSlotId, seatId);
    }

    /**
     * 依 活動時段 ID 查詢座位狀態
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @return 座位狀態清單
     */
    @Override
    @Transactional(readOnly = true)
    public List<SeatStatus> getAllSeatStatusByActivityTimeSlotID(Integer activityTimeSlotId) {
        return seatStatusDao.findAllByActivityTimeSlotId(activityTimeSlotId);
    }

    /**
     * 為了在seatSelectPage顯示不可選取的座位而寫的方法
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @return 座位名稱清單
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> getUnavailableSeatNames(Integer activityTimeSlotId) {
        // 假設狀態 2 和 3 表示座位不可用
        return seatStatusDao.findUnavailableSeatNamesByActivityTimeSlotId(activityTimeSlotId, 2, 3);
    }

    /**
     * 將狀態設置為 3（保留）
     *
     * @param seatId
     *          座位 ID
     * @param activityTimeSlotId
     *          活動時段 ID
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateSeatStatusToReserved(Integer seatId, Integer activityTimeSlotId) {
        updateSeatStatus(seatId, activityTimeSlotId, 3);
    }

    /**
     * 更新座位狀態的通用方法
     *
     * @param seatId
     *          座位 ID
     * @param activityTimeSlotId
     *          活動時段 ID
     * @param newStatus
     *          新的座位狀態
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateSeatStatus(Integer seatId, Integer activityTimeSlotId, Integer newStatus) {
        SeatStatus seatStatus = seatStatusDao.findSeatStatusByActivityTimeSlotIdAndSeatId(activityTimeSlotId, seatId);
        if (seatStatus != null) {
            seatStatus.setSeatStatus(newStatus);
            seatStatusDao.update(seatStatus);
        } else {
            throw new RuntimeException(
                "SeatStatus not found for seatID: " + seatId + " and activityTimeSlotID: " + activityTimeSlotId);
        }
    }

    /**
     * 獲取 seatStatus 為 2 的座位名稱
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @return 座位名稱清單
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> getSeatNamesWithStatus2(Integer activityTimeSlotId) {
        return seatStatusDao.findSeatNamesWithStatus2(activityTimeSlotId);
    }

    /**
     * 獲取 seatStatus 為 3 的座位名稱
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @return 座位名稱清單
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> getSeatNamesWithStatus3(Integer activityTimeSlotId) {
        return seatStatusDao.findSeatNamesWithStatus3(activityTimeSlotId);
    }

}