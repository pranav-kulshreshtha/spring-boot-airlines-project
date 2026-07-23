package com.msp.repositories;

import com.msp.enums.AncillaryType;
import com.msp.models.FlightCabinAncillary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlightCabinAncillaryRepository
        extends JpaRepository<FlightCabinAncillary, Long> {
    List<FlightCabinAncillary> findByFlightIdAndCabinClassId(
            Long flightId, Long cabinClassId);
    FlightCabinAncillary findByFlightIdAndCabinClassIdAndAncillary_Type(
            Long flightId, Long cabinClassId, AncillaryType ancillaryType);
    List<FlightCabinAncillary> findAllByFlightIdAndCabinClassIdAndAncillary_Type(
            Long flightId, Long cabinClassId, AncillaryType ancillaryType);
}
