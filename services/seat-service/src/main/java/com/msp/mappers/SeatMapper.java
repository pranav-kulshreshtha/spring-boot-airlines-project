package com.msp.mappers;

import com.msp.models.Seat;
import com.msp.payloads.responses.SeatResponse;

public class SeatMapper {

    public static SeatResponse toResponse(Seat seat) {
        if(seat == null) return null;

        return SeatResponse.builder()
                .id(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .seatRow(seat.getSeatRow())
                .columnLetter(seat.getColumnLetter())
                .seatType(seat.getSeatType())

                .isAvailable(seat.getIsAvailable())
                .isBlocked(seat.getIsBlocked())
                .isEmergencyExist(seat.getIsEmergencyExit())
                .isActive(seat.getIsActive())

                .basePrice(seat.getBasePrice())
                .premiumSurcharge(seat.getPremiumSuperCharge())
                .totalPrice(seat.getTotalPrice())

                .hasExtraLegroom(seat.getHasExtraLegroom())
                .hasPowerOutlet(seat.getHasPowerOutlet())
                .hasTvScreen(seat.getHasTvScreen())
                .hasExtraWidth(seat.getHasExtraWidth())

                .seatPitch(seat.getSeatPitch())
                .seatWidth(seat.getSeatWidth())

                .seatMapId(seat.getSeatMap() != null ?
                        seat.getSeatMap().getId() : null)
                .seatMapName(seat.getSeatMap() != null ?
                        seat.getSeatMap().getName() : null)

                .cabinClassId(seat.getCabinClass() != null ?
                        seat.getCabinClass().getId() : null)
                .cabinClassName(seat.getCabinClass() != null ?
                        seat.getCabinClass().getName().toString() : null)

                .createdAt(seat.getCreatedAt())
                .updatedAt(seat.getUpdatedAt())
                .createdBy(seat.getCreatedBy())
                .updatedBy(seat.getUpdatedBy())

                .isPremiumSeat(seat.getPremiumSuperCharge() != null &&
                        seat.getPremiumSuperCharge() > 0)
//                .isBookable(seat.isBookable())
                .fullPosition(seat.getFullPosition())
                .seatCharacteristics(null)
                .build();
    }
}
