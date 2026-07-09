package com.msp.mappers;

import com.msp.models.Flight;
import com.msp.models.FlightSchedule;
import com.msp.payloads.requests.FlightScheduleRequest;
import com.msp.payloads.responses.AirportResponse;
import com.msp.payloads.responses.FlightScheduleResponse;

public class FlightScheduleMapper {
    public static FlightSchedule toEntity(FlightScheduleRequest request,
                                          Flight flight) {
        if(request == null || flight == null)return null;

        return FlightSchedule.builder()
                .flight(flight)
                .arrivalAirportId(flight.getArrivalAirportId())
                .departureAirportId(flight.getDepartureAirportId())
                .arrivalTime(request.getArrivalTime())
                .departureTime(request.getDepartureTime())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .operatingDays(request.getOperatingDays())
                .isActive(
                        request.getIsActive() != null ? request.getIsActive() : true)
                .build();
    }

    public static FlightScheduleResponse toResponse(
            FlightSchedule fs,
            AirportResponse arrival,
            AirportResponse departure) {

        if (fs == null) return null;

        return FlightScheduleResponse.builder()
                .id(fs.getId())
                .flightId(fs.getFlight() != null ? fs.getFlight().getId() : null)
                .flightNumber(fs.getFlight() != null ? fs.getFlight().getFlightNumber() : null)
                .departureAirport(departure)
                .arrivalAirport(arrival)
                .departureTime(fs.getDepartureTime())
                .arrivalTime(fs.getArrivalTime())
                .startDate(fs.getStartDate())
                .endDate(fs.getEndDate())
                .operatingDays(fs.getOperatingDays())
                .isActive(fs.isActive())
                .build();
    }

    public static void updateEntity(FlightScheduleRequest request, FlightSchedule existing) {
        if (request == null || existing == null) return;

        if (request.getDepartureTime() != null)
            existing.setDepartureTime(request.getDepartureTime());

        if (request.getArrivalTime() != null)
            existing.setArrivalTime(request.getArrivalTime());

        if (request.getStartDate() != null)
            existing.setStartDate(request.getStartDate());

        if (request.getEndDate() != null)
            existing.setEndDate(request.getEndDate());

        if (request.getOperatingDays() != null)
            existing.setOperatingDays(request.getOperatingDays());

        if (request.getIsActive() != null)
            existing.setActive(request.getIsActive());
    }
}
