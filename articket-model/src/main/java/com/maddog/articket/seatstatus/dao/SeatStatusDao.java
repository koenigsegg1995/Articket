package com.maddog.articket.seatstatus.dao;

import com.maddog.articket.seatstatus.entity.SeatStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 座位狀態 DAO
 */
@Mapper
public interface SeatStatusDao {

    /**
     * 更新
     *
     * @param seatStatus
     *          座位狀態
     * @return 成功筆數
     */
    int update(SeatStatus seatStatus);

    /**
     * 依 活動時段 ID 和座位 ID 查詢
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @param seatId
     *          座位 ID
     * @return 座位狀態
     */
    SeatStatus findSeatStatusByActivityTimeSlotIdAndSeatId(@Param("activityTimeSlotId") Integer activityTimeSlotId,
                                                           @Param("seatId") Integer seatId);

    /**
     * 依 活動時段 ID 查詢座位狀態
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @return 座位狀態清單
     */
    List<SeatStatus> findAllByActivityTimeSlotId(Integer activityTimeSlotId);

    /**
     * 為了在seatSelectPage顯示不可選取的座位而寫的方法
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @param status1
     *          狀態 1
     * @param status2
     *          狀態 2
     * @return 座位名稱清單
     */
    List<String> findUnavailableSeatNamesByActivityTimeSlotId(@Param("activityTimeSlotId") Integer activityTimeSlotId,
                                                              @Param("status1") Integer status1,
                                                              @Param("status2") Integer status2);

    /**
     * 獲取 seatStatus 為 2 的座位名稱
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @return 座位名稱清單
     */
    List<String> findSeatNamesWithStatus2(Integer activityTimeSlotId);

    /**
     * 獲取 seatStatus 為 3 的座位名稱
     *
     * @param activityTimeSlotId
     *          活動時段 ID
     * @return 座位名稱清單
     */
    List<String> findSeatNamesWithStatus3(Integer activityTimeSlotId);

}