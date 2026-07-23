package com.msp.services;

import com.msp.enums.AncillaryType;
import com.msp.payloads.requests.FlightCabinAncillaryRequest;
import com.msp.payloads.responses.FlightCabinAncillaryResponse;
import java.util.List;

public interface FlightCabinAncillaryService {
    FlightCabinAncillaryResponse createFlightCabinAncillary(
            FlightCabinAncillaryRequest request) throws Exception;
    FlightCabinAncillaryResponse getById(Long id) throws Exception;
    List<FlightCabinAncillaryResponse> getByFlightAndCabinClass(Long flightId,
                                                                Long cabinClassId);
    List<FlightCabinAncillaryResponse> getAllByIds(List<Long> ids);
    FlightCabinAncillaryResponse getByFlightIdAndCabinClassIdAndType(
            Long flightId,
            Long cabinClassId,
            AncillaryType type);
    List<FlightCabinAncillaryResponse> getAllByFlightIdAndCabinClassIdAndType(
            Long flightId,
            Long cabinClassId,
            AncillaryType type
    );
    FlightCabinAncillaryResponse updateFlightCabinAncillary(
            Long id, FlightCabinAncillaryRequest request) throws Exception;
    void deleteFlightCabinAncillary(Long id) throws Exception;
    Double calculateAncillaryPrice(List<Long> ancillaryIds);
}
