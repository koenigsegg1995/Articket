package com.maddog.articket.seat.service.impl;

import com.maddog.articket.seat.dao.SeatDao;
import com.maddog.articket.seat.service.pri.SeatService;
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
    private SeatDao seatDao;

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
        return seatDao.findSeatIdByVenueIdAndSeatName(venueId, seatName);
    }

}