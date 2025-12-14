package com.maddog.articket.seat.model.service.impl;

import com.maddog.articket.seat.model.entity.SeatRepository;
import com.maddog.articket.seat.model.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public Integer findSeatId(Integer venueId, String seatName) {
        Seat seat = seatRepository.findByVenueIdAndSeatName(venueId, seatName);
        return seat != null ? seat.getSeatID() : null;
    }
}