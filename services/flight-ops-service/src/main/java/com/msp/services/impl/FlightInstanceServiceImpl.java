package com.msp.services.impl;

import com.msp.mappers.FlightInstanceMapper;
import com.msp.models.Flight;
import com.msp.models.FlightInstance;
import com.msp.payloads.requests.FlightInstanceRequest;
import com.msp.payloads.responses.AircraftResponse;
import com.msp.payloads.responses.AirlineResponse;
import com.msp.payloads.responses.AirportResponse;
import com.msp.payloads.responses.FlightInstanceResponse;
import com.msp.repositories.FlightInstanceRepository;
import com.msp.repositories.FlightRepository;
import com.msp.services.FlightInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class FlightInstanceServiceImpl implements FlightInstanceService {

    private final FlightRepository flightRepository;
    private final FlightInstanceRepository flightInstanceRepository;

    @Override
    public FlightInstanceResponse createFlightInstance(Long airlineId, FlightInstanceRequest request) throws Exception {
        // todo : watch airlineId
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new Exception("Flight not found!"));

        // todo : service to service communication
        AircraftResponse aircraft = AircraftResponse.builder()
                .id(1L)
                .totalSeats(90)
                .build();

        FlightInstance flightInstance = FlightInstanceMapper.toEntity(request, flight);
        flightInstance.setTotalSeats(aircraft.getTotalSeats());
        flightInstance.setAvailableSeats(aircraft.getTotalSeats());

        FlightInstance saved = flightInstanceRepository.save(flightInstance);

        //todo : create seat instances

        return convertToFlightInstanceResponse(saved);
    }

    @Override
    public FlightInstanceResponse getFlightInstanceById(Long id) throws Exception {
        FlightInstance flightInstance = flightInstanceRepository.findById(id)
                .orElseThrow(() -> new Exception("Flight instance not found with id!"));
        return convertToFlightInstanceResponse(flightInstance);
    }

    @Override
    public Page<FlightInstanceResponse> getByAirlineId(Long airlineId, Long departureAirportId, Long arrivalAirportId, Long flightId, LocalDate onDate, Pageable pageable) {
        // todo : watch airlineId
        LocalDateTime start = onDate!=null ? onDate.atStartOfDay() : null;
        LocalDateTime end = onDate!=null ? onDate.plusDays(1).atStartOfDay()
                : null;

        return flightInstanceRepository.findByAirlineId(
                airlineId, departureAirportId, arrivalAirportId, flightId, start,
                end, pageable
        ).map(this::convertToFlightInstanceResponse);
    }

    @Override
    public FlightInstanceResponse updateFlightResponse(Long id, FlightInstanceRequest request) throws Exception {
        FlightInstance existing = flightInstanceRepository.findById(id)
                .orElseThrow(() -> new Exception("Flight instance not found with id!"));

        FlightInstanceMapper.updateEntity(request, existing);
        FlightInstance updated = flightInstanceRepository.save(existing);
        return convertToFlightInstanceResponse(updated);
    }

    @Override
    public void deleteFlightInstance(Long id) throws Exception {
        FlightInstance flight = flightInstanceRepository.findById(id)
                .orElseThrow(() -> new Exception("Flight instance not found with id!"));

        flightInstanceRepository.delete(flight);
    }

    private FlightInstanceResponse convertToFlightInstanceResponse(FlightInstance flightInstance) {
        AirlineResponse airline = AirlineResponse.builder()
                .id(flightInstance.getAirlineId())
                .build();
        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(flightInstance.getArrivalAirportId())
                .build();
        AirportResponse departureAirport = AirportResponse.builder()
                .id(flightInstance  .getDepartureAirportId())
                .build();
        AircraftResponse aircraft = AircraftResponse.builder()
                .id(flightInstance.getFlight().getAircraftId())
                .build();

        return FlightInstanceMapper.toResponse(flightInstance,
                aircraft,
                airline,
                departureAirport,
                arrivalAirport);
    }
}
