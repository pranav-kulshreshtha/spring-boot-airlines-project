package com.msp.services;

import com.msp.payloads.requests.SeatRequest;
import com.msp.payloads.responses.SeatResponse;
import java.util.List;

public interface SeatService {
    void generateSeats(Long seatMapId) throws Exception;
    List<SeatResponse> getAll();
    SeatResponse updateSeats(Long seatId, SeatRequest seatRequest);
}
