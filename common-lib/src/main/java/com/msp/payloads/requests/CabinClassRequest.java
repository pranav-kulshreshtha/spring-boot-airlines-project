package com.msp.payloads.requests;

import com.msp.enums.CabinClassType;
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
public class CabinClassRequest {

    @NotNull(message = "Cabin class name cannot be blank!")
    private CabinClassType name;

    @NotBlank(message = "Cabin class code cannot be blank!")
    private String code;

    private String description;

    @NotNull(message = "Aircraft ID is required!")
    private Long aircraftId;

    private Integer displayOrder;
    private Boolean isActive;
    private Boolean isBookable;
    private Integer typicalSeatPitch;
    private Integer typicalSeatWidth;
    private String seatType;
}
