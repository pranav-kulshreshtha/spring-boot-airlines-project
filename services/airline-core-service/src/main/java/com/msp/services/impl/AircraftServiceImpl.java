package com.msp.services.impl;

import com.msp.mappers.AircraftMapper;
import com.msp.models.Aircraft;
import com.msp.models.Airline;
import com.msp.payloads.requests.AircraftRequest;
import com.msp.payloads.responses.AircraftResponse;
import com.msp.repositories.AircraftRepository;
import com.msp.repositories.AirlineRepository;
import com.msp.services.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {

    private final AirlineRepository airlineRepository;
    private final AircraftRepository aircraftRepository;

    @Override
    public AircraftResponse createAircraft(AircraftRequest aircraftRequest, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new Exception("Airline does not exist by given owner id!"));

        Aircraft aircraft = AircraftMapper.toEntity(aircraftRequest, airline);
        if(aircraftRepository.existsByCode(aircraft.getCode())) {
            throw new Exception("Code already exists with another aircraft!");
        }

        if(aircraft.getSeatingCapacity() < aircraft.getTotalSeats()) {
            throw new Exception("Seating capacity can't exceed total seats!");
        }

        return AircraftMapper.toResponse(
                aircraftRepository.save(aircraft)
        );
    }

    @Override
    public AircraftResponse getAircraftById(Long id) throws Exception {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new Exception("Aircraft does not exist with given id!"));

        return AircraftMapper.toResponse(aircraft);
    }

    @Override
    public List<AircraftResponse> listAllAircraftByOwner(Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new Exception("This owner doesn't have any airline!"));
        return aircraftRepository.findByAirlineId(airline.getId())
                .stream()
                .map(AircraftMapper::toResponse).toList();
    }

    @Override
    public AircraftResponse updateAircraft(Long id, AircraftRequest aircraftRequest, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new Exception("This owner doesn't have any airline!"));
        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(id, airline.getId());
        if(aircraft == null){
            throw new Exception("Aircraft does not exist for given id!");
        }

        if(aircraftRequest.getCode()!=null
                && !aircraft.getCode().equals(aircraftRequest.getCode())
                && aircraftRepository.existsByCode(aircraft.getCode())) {
            throw new Exception("Code already exists with another aircraft!");
        }

        AircraftMapper.updateEntity(aircraft, aircraftRequest);

        return AircraftMapper.toResponse(aircraftRepository.save(aircraft));
    }

    @Override
    public void deleteAircraft(Long id, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new Exception("This owner doesn't have any airline!"));
        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(id, airline.getId());
        if(aircraft == null){
            throw new Exception("Aircraft does not exist for given id!");
        }
        aircraftRepository.delete(aircraft);
    }
}
