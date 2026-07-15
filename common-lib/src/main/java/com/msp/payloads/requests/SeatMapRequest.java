package com.msp.payloads.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatMapRequest {

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @Positive
    @NotNull(message = "Total rows cannot be empty!")
    private int totalRows;

    @Positive
    @NotNull(message = "Right seats per row is required!")
    private int rightSeatsPerRow;

    @Positive
    @NotNull(message = "Left seats per row is required!")
    private int leftSeatsPerRow;

    private Long cabinClassId;
}
