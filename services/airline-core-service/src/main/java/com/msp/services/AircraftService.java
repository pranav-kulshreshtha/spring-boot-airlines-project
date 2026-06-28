package com.msp.services;

import com.msp.payloads.requests.AircraftRequest;
import com.msp.payloads.responses.AircraftResponse;

import java.util.List;

public interface AircraftService {

    AircraftResponse createAircraft(AircraftRequest aircraftRequest, Long ownerId) throws Exception;
    AircraftResponse getAircraftById(Long id) throws Exception;
    List<AircraftResponse> listAllAircraftByOwner(Long ownerId) throws Exception;
    AircraftResponse updateAircraft(Long id, AircraftRequest aircraftRequest, Long ownerId) throws Exception;
    void deleteAircraft(Long id, Long ownerId) throws Exception;
}
