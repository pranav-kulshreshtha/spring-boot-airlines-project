package com.msp.payloads.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaggagePolicyRequest {

    @NotBlank(message = "Policy name is required!")
    private String name;

    @NotNull(message = "Fare ID is required!")
    private Long fareId;

    private String description;

    @PositiveOrZero
    private Double cabinBaggageMaxWeight;

    @PositiveOrZero
    private Integer cabinBaggagePieces = 1;

    @PositiveOrZero
    private Double cabinBaggageWeightPerPiece;

    @PositiveOrZero
    private Integer cabinBaggageMaxDimension;

    @PositiveOrZero
    private Double checkinBaggageMaxWeight;

    @PositiveOrZero
    private Integer checkinBaggagePieces = 1;

    @PositiveOrZero
    private Double checkinBaggageWeightPerPiece;

    @PositiveOrZero
    private Integer freeCheckedBaggageAllowance = 0;

    private Boolean priorityBaggage = false;
    private Boolean extraBaggageAllowance = false;
}
