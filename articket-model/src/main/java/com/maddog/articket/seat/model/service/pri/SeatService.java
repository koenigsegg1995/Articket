package com.maddog.articket.seat.model.service.pri;

/**
 * 座位 Service interface
 */
public interface SeatService {

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
    Integer findSeatId(Integer venueId, String seatName);

}