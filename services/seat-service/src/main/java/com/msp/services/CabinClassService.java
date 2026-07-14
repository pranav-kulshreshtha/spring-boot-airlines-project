package com.msp.services;

import com.msp.enums.CabinClassType;
import com.msp.payloads.requests.CabinClassRequest;
import com.msp.payloads.responses.CabinClassResponse;
import java.util.List;

public interface CabinClassService {
    CabinClassResponse createCabinClass(CabinClassRequest request) throws Exception;
    CabinClassResponse getCabinClassById(Long id) throws Exception;
    List<CabinClassResponse> getCabinClassByAircraftId(Long aircraftId);
    CabinClassResponse getByAircraftIdAndName(Long aircraftId, CabinClassType name);
    CabinClassResponse updateCabinClass(Long id, CabinClassRequest request) throws Exception;
    void deleteCabinClass(Long id) throws Exception;
}
