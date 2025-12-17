package com.maddog.articket.seat.model.service.impl;

import com.maddog.articket.seat.model.entity.SeatDao;
import com.maddog.articket.seat.model.service.pri.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 座位 Service Implementation
 */
@Service("seatService")
public class SeatServiceImpl implements SeatService {

    /**
     * 座位 DAO
     */
    @Autowired
    private SeatDao seatDap;

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
    @Override
    @Transactional(readOnly = true)
    public Integer findSeatId(Integer venueId, String seatName) {
        return seatDap.findSeatIdByVenueIdAndSeatName(venueId, seatName);
    }

}