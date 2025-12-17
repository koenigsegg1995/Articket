package com.maddog.articket.seat.model.entity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 座位 DAO
 */
@Mapper
public interface SeatDao {

    /**
     * 依場館 ID 與座位代號查詢座位 ID
     *
     * @param venueId
     *          Integer
     * @param seatName
     *          String
     * @return 座位 ID
     *          Integer
     */
    Integer findSeatIdByVenueIdAndSeatName(@Param("venueId") Integer venueId, @Param("seatName") String seatName);

}
