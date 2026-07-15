package com.msp.payloads.requests;

import com.msp.enums.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequest {

    @NotBlank(message = "Seat number is required!")
    private String seatNumber;

    @NotNull(message = "Seat row is required!")
    private Integer seatRow;

    private Character columnLetter;

    @NotNull(message = "Seat type is required!")
    private SeatType seatType;

    @NotNull(message = "Seat map is required!")
    private Long seatMapId;

    private Long cabinClassId;

    private Boolean isAvailable;
    private Boolean isBlocked;
    private Boolean isEmergencyExist;
    private Boolean isActive;

    private Boolean hasExtraLegroom;
    private Boolean hasPowerOutlet;
    private Boolean hasTvScreen;
    private Boolean hasExtraWidth;

    private Integer seatPitch;
    private Integer seatWidth;
}
