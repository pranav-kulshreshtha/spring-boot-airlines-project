package com.msp.services;

import com.msp.enums.AirlineStatus;
import com.msp.payloads.requests.AirlineRequest;
import com.msp.payloads.responses.AirlineDropdownItem;
import com.msp.payloads.responses.AirlineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirlineService {

    AirlineResponse createAirline(AirlineRequest request, Long ownerId);
    AirlineResponse getAirlineByOwner(Long ownerId) throws Exception;
    AirlineResponse getAirlineById(Long id) throws Exception;
    AirlineResponse updateAirline(AirlineRequest request, Long ownerId) throws Exception;
    Page<AirlineResponse> getAllAirlines(Pageable pageable);
    void deleteAirline(Long id, Long ownerId) throws Exception;
    AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) throws Exception;
    List<AirlineDropdownItem> getAirlineDropdown();
}
