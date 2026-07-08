package com.maddog.articket.seatstatus.service.pri;

import com.maddog.articket.seatstatus.entity.SeatStatus;

import java.util.List;

/**
 * 座位狀態 Service Interface
 */
public interface SeatStatusService {

    /**
     * 依 活動時段 ID 和座位 ID 查詢
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @param seatId
     *          座位 ID
     * @return 座位狀態
     */
    SeatStatus getSeatStatusByActivityTimeSlotIdAndSeatId(Integer activityTimeSlotId,
                                                          Integer seatId);

    /**
     * 依 活動時段 ID 查詢座位狀態
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @return 座位狀態清單
     */
    List<SeatStatus> getAllSeatStatusByActivityTimeSlotId(Integer activityTimeSlotId);

    /**
     * 為了在seatSelectPage顯示不可選取的座位而寫的方法
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @return 座位名稱清單
     */
    List<String> getUnavailableSeatNames(Integer activityTimeSlotId);

    /**
     * 將狀態設置為 3（保留）
     *
     * @param seatId
     *          座位 ID
     * @param activityTimeSlotId
     *          活動時段 ID
     */
    void updateSeatStatusToReserved(Integer seatId, Integer activityTimeSlotId);

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
    void updateSeatStatus(Integer seatId, Integer activityTimeSlotId, Integer newStatus);

    /**
     * 獲取 seatStatus 為 2 的座位名稱
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @return 座位名稱清單
     */
    List<String> getSeatNamesWithStatus2(Integer activityTimeSlotId);

    /**
     * 獲取 seatStatus 為 3 的座位名稱
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @return 座位名稱清單
     */
    List<String> getSeatNamesWithStatus3(Integer activityTimeSlotId);

}