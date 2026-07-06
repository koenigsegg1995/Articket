package com.maddog.articket.seat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SeatPriceData {

    private List<String> reservedSeats;
    private Map<String, Integer> prices;

}