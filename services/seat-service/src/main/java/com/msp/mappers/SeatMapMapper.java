package com.msp.mappers;

import com.msp.models.CabinClass;
import com.msp.models.SeatMap;
import com.msp.payloads.requests.SeatMapRequest;
import com.msp.payloads.responses.SeatMapResponse;

public class SeatMapMapper {
    public static SeatMap toEntity(SeatMapRequest request, CabinClass cabinClass) {
        if(request == null)return null;

        return SeatMap.builder()
                .name(request.getName())
                .totalRows(request.getTotalRows())
                .rightSeatsPerRow(request.getRightSeatsPerRow())
                .leftSeatsPerRow(request.getLeftSeatsPerRow())
//                .airlineId(airlineId)
                .cabinClass(cabinClass)
                .build();
    }

    public static SeatMapResponse toResponse(SeatMap seatMap) {
        if(seatMap == null) return null;

        // todo : watch seats
//        List<Seat> seats = seatMap.getSeats();

        return SeatMapResponse.builder()
                .id(seatMap.getId())
                .name(seatMap.getName())
                .totalRows(seatMap.getTotalRows())
                .rightSeatsPerRow(seatMap.getRightSeatsPerRow())
                .leftSeatsPerRow(seatMap.getLeftSeatsPerRow())
                .airlineId(seatMap.getAirlineId())
                .cabinClassId(seatMap.getCabinClass() != null ?
                        seatMap.getCabinClass().getId() : null)
                .cabinClassName(seatMap.getCabinClass() != null ?
                        seatMap.getCabinClass().getName().toString() : null)
                .cabinClassCode(seatMap.getCabinClass() != null ?
                        seatMap.getCabinClass().getCode() : null)

//                .totalSeats(totalSeats)
//                .windowSeats(null)
//                .aisleSeats(null)
//                .middleSeats(null)
//                .premiumSeats(null)
//                .emergencyExitSeats(null)
                .build();
    }

    public static void updateEntity(
            SeatMapRequest request,
            SeatMap existing
    ) {
        if(request == null || existing == null) return;

        if(request.getName() != null) existing.setName(request.getName());
        existing.setTotalRows(request.getTotalRows());
        existing.setRightSeatsPerRow(request.getRightSeatsPerRow());
        existing.setLeftSeatsPerRow(request.getLeftSeatsPerRow());
    }
}
