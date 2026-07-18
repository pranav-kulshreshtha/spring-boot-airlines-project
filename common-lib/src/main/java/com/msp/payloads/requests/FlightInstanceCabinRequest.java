package com.msp.payloads.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightInstanceCabinRequest {

    @NotNull(message = "Flight ID is required!")
    private Long flightId;

    @NotNull(message = "Flight instance ID is required!")
    private Long flightInstanceId;

    @NotNull
    private Long cabinClassId;

    @NotNull
    @Positive
    private Double baseFare;

    @NotNull
    private Double windowSurcharge;

    @NotNull
    private Double aisleSurcharge;

    @NotNull
    @PositiveOrZero
    private Double taxesAndFees;

    @NotNull
    @PositiveOrZero
    private Double airlineFees;

    private Double currentPrice;

    private Boolean isActive;
    private Boolean canBook;
}
