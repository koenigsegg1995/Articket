package com.maddog.articket.seat.model;

import java.util.List;
import java.util.Map;

public class SeatPriceData {
    private List<String> reservedSeats;
    private Map<String, Integer> prices;

    // Getters and setters
    public List<String> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<String> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public Map<String, Integer> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, Integer> prices) {
        this.prices = prices;
    }
}