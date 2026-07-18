package com.msp.mappers;

import com.msp.enums.SeatAvailabilityStatus;
import com.msp.models.SeatInstance;
import com.msp.payloads.responses.SeatInstanceResponse;

public class SeatInstanceMapper {
    public static SeatInstanceResponse toResponse(SeatInstance seatInstance) {
        if(seatInstance == null) return null;

        return SeatInstanceResponse.builder()
                .id(seatInstance.getId())
                .flightId(seatInstance.getFlightId())
                .seatId(seatInstance.getSeat() != null ?
                        seatInstance.getSeat().getId() : null)
                .seatNumber(seatInstance.getSeat() != null ?
                        seatInstance.getSeat().getSeatNumber() : null)
                .seatType(seatInstance.getSeat() != null &&
                        seatInstance.getSeat().getSeatType() != null ?
                        seatInstance.getSeat().getSeatType().name() : null)
                .seatPosition(seatInstance.getSeat() != null ?
                        seatInstance.getSeat().getFullPosition() : null)
                .seat(SeatMapper.toResponse(seatInstance.getSeat()))
                .price((seatInstance.getFare() != null ? seatInstance.getFare() : 0)
                        + (seatInstance.getPremiumSupercharge() != null ?
                        seatInstance.getPremiumSupercharge() : 0))
                .status(seatInstance.getSeatAvailabilityStatus())
                .flightInstanceId(seatInstance.getFlightInstanceId())
                .flightCabinId(seatInstance.getFlightInstanceCabin() != null ?
                        seatInstance.getFlightInstanceCabin().getId() : null)
                .fare(seatInstance.getFare())
                .price(seatInstance.getPremiumSupercharge())
                .version(seatInstance.getVersion())
                .createdAt(seatInstance.getCreatedAt())
                .updatedAt(seatInstance.getUpdatedAt())
                .isBooked(seatInstance.getIsBooked())
                .isAvailable(seatInstance.getIsAvailable())
                .isOccupied(SeatAvailabilityStatus.BOOKED.equals(
                        seatInstance.getSeatAvailabilityStatus()))
                .build();
    }
}
