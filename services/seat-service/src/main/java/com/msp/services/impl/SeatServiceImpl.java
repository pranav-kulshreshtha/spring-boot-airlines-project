package com.msp.services.impl;

import com.msp.enums.SeatType;
import com.msp.mappers.SeatMapper;
import com.msp.models.Seat;
import com.msp.models.SeatMap;
import com.msp.payloads.requests.SeatRequest;
import com.msp.payloads.responses.SeatResponse;
import com.msp.repositories.SeatMapRepository;
import com.msp.repositories.SeatRepository;
import com.msp.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final SeatMapRepository seatMapRepository;

    @Override
    public void generateSeats(Long seatMapId) throws Exception {
        boolean exists = seatRepository.existsBySeatMapId(seatMapId);

        if(exists) {
            throw new Exception("Seats already created for seat map!");
        }
        SeatMap seatMap = seatMapRepository.findById(seatMapId)
                .orElseThrow(() -> new Exception("Seat map not found!"));
        int leftSeatsPerRow = seatMap.getLeftSeatsPerRow();
        int rightSeatsPerRow = seatMap.getRightSeatsPerRow();
        int rows = seatMap.getTotalRows();
        int seatsPerRow = leftSeatsPerRow + rightSeatsPerRow;

        List<Seat> seats = new ArrayList<>();

        for(int row = 1; row <= rows; row++) {
            for(int col = 0; col<seatsPerRow; col++) {
                String seatNumber = row + getSeatLetter(col);
                SeatType type = getSeatType(col, leftSeatsPerRow, rightSeatsPerRow);
                Seat seat = Seat.builder()
                        .seatNumber(seatNumber)
                        .seatRow(row)
                        .columnLetter(getSeatLetter(col).charAt(0))
                        .seatType(type)
                        .seatMap(seatMap)
                        .build();

                seats.add(seat);
            }
        }

        seatRepository.saveAll(seats);
    }

    @Override
    public List<SeatResponse> getAll() {
        return seatRepository.findAll()
                .stream().map(SeatMapper::toResponse)
                .toList();
    }

    @Override
    public SeatResponse updateSeats(Long seatId, SeatRequest seatRequest) {
        return null;
    }

    private String getSeatLetter(int col) {
        StringBuilder sb = new StringBuilder();
        while(col >= 0) {
            sb.insert(0, (char)('A'+(col%26)));
            col = col/26 - 1;
        }

        return sb.toString();
    }

    private SeatType getSeatType(int col, int leftSeatsPerRow, int rightSeatsPerRow) {
        int totalSeats = leftSeatsPerRow + rightSeatsPerRow;

        //case : window seat
        if(col==0 || col==totalSeats-1)return SeatType.WINDOW;
        //case : left aisle
        if(col==leftSeatsPerRow-1)return SeatType.AISLE;
        //case : right aisle
        if(col==leftSeatsPerRow)return SeatType.AISLE;

        return SeatType.MIDDLE;
    }
}
