package com.msp.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatMapResponse {
    private Long id;

    private String name;
    private int totalRows;
    private int totalSeats;

    private Long airlineId;
    private String airlineName;
    private String airlineCode;

    private Long cabinClassId;
    private String cabinClassName;
    private String cabinClassCode;

    private List<SeatResponse> seats;

    private Integer availableSeats;
    private Integer occupiedSeats;

    private Integer windowSeats;
    private Integer aisleSeats;
    private Integer middleSeats;
    private Integer premiumSeats;
    private Integer emergencyExitSeats;

    private int rightSeatsPerRow;
    private int leftSeatsPerRow;
}
