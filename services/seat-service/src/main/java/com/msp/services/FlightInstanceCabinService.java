package com.msp.services;

import com.msp.payloads.requests.FlightInstanceCabinRequest;
import com.msp.payloads.responses.FlightInstanceCabinResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightInstanceCabinService {
    FlightInstanceCabinResponse createFlightInstanceCabin(
            FlightInstanceCabinRequest request) throws Exception;

    FlightInstanceCabinResponse getFlightInstanceCabinById(Long id) throws Exception;

    Page<FlightInstanceCabinResponse>
        getByFlightInstanceId(Long id, Pageable pageable);

    FlightInstanceCabinResponse getByFlightInstanceIdAndCabinClassId(
            Long flightInstanceId,
            Long cabinClassId);

    FlightInstanceCabinResponse updateFlightInstanceCabin(
            Long id, FlightInstanceCabinRequest request) throws Exception;

    void deleteFlightInstanceCabin(Long id) throws Exception;
}
