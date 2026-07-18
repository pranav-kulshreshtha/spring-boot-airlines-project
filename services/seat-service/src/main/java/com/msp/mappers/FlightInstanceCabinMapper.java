package com.msp.mappers;

import com.msp.models.FlightInstanceCabin;
import com.msp.payloads.responses.FlightInstanceCabinResponse;

public class FlightInstanceCabinMapper {
    public static FlightInstanceCabinResponse toResponse(
            FlightInstanceCabin flightInstanceCabin) {
        if(flightInstanceCabin == null)return null;

        return FlightInstanceCabinResponse.builder()
                .id(flightInstanceCabin.getId())
                .flightInstanceId(flightInstanceCabin.getFlightInstanceId())
                .cabinClassType(flightInstanceCabin.getCabinClass().getName())
                .cabinClass(CabinClassMapper.toResponse(
                        flightInstanceCabin.getCabinClass(),
                        flightInstanceCabin.getCabinClass().getSeatMap()))
                .seats(flightInstanceCabin.getSeats()!=null ?
                        flightInstanceCabin.getSeats().stream()
                        .map(SeatInstanceMapper::toResponse).toList() : null)
                .seatMap(flightInstanceCabin.getCabinClass().getSeatMap() != null
                    ? SeatMapMapper.toSimpleResponse(
                            flightInstanceCabin.getCabinClass().getSeatMap())
                        : null)
                .totalSeats(flightInstanceCabin.getTotalSeats())
                .bookedSeats(flightInstanceCabin.getBookedSeats())
                .availableSeats(flightInstanceCabin.getAvailableSeats())
                .build();
    }
}
