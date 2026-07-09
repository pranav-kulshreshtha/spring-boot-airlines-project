package com.msp.services;

import com.msp.enums.FlightStatus;
import com.msp.payloads.requests.FlightRequest;
import com.msp.payloads.responses.FlightResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightService {

    FlightResponse createFlight(Long airlineId, FlightRequest request) throws Exception;
    Page<FlightResponse> getFlightsByAirline(Long airlineId,
                                             Long departureAirportId,
                                             Long arrivalAirportId,
                                             Pageable pageable);
    FlightResponse getFlightById(Long id) throws Exception;
    FlightResponse updateFlight(Long id, FlightRequest flightRequest) throws Exception;
    FlightResponse changeStatus(Long id, FlightStatus status) throws Exception;
    void deleteFlight(Long airlineId, Long id) throws Exception;
}
