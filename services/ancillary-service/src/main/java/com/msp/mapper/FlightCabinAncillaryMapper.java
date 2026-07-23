package com.msp.mapper;

import com.msp.models.Ancillary;
import com.msp.models.FlightCabinAncillary;
import com.msp.payloads.requests.FlightCabinAncillaryRequest;
import com.msp.payloads.responses.FlightCabinAncillaryResponse;
import com.msp.payloads.responses.InsuranceCoverageResponse;

import java.util.List;

public class FlightCabinAncillaryMapper {

    public static FlightCabinAncillary toEntity(
            FlightCabinAncillaryRequest request,
            Ancillary ancillary
    ) {
        if (request == null) {
            return null;
        }

        return FlightCabinAncillary.builder()
                .flightId(request.getFlightId())
                .cabinClassId(request.getCabinClassId())
                .ancillary(ancillary)
                .available(request.getAvailable() != null ? request.getAvailable() : true)
                .maxQuantity(request.getMaxQuantity())
                .price(request.getPrice())
                .includedInFare(request.getIncludedInFare() != null ? request.getIncludedInFare() : false)
                .build();
    }

    public static FlightCabinAncillaryResponse toResponse(
            FlightCabinAncillary flightCabinAncillary,
            List<InsuranceCoverageResponse> coverages) {
        if (flightCabinAncillary == null) {
            return null;
        }

        return FlightCabinAncillaryResponse.builder()
                .id(flightCabinAncillary.getId())
                .flightId(flightCabinAncillary.getFlightId())
                .cabinClassId(flightCabinAncillary.getCabinClassId())
                .ancillaryResponse(AncillaryMapper.toResponse(
                        flightCabinAncillary.getAncillary(), coverages))
                .available(flightCabinAncillary.getAvailable())
                .maxQuantity(flightCabinAncillary.getMaxQuantity())
                .price(flightCabinAncillary.getPrice())
                .includedInFare(flightCabinAncillary.getIncludedInFare())
                .build();
    }

    public static void updateEntity(
            FlightCabinAncillaryRequest request,
            FlightCabinAncillary existing,
            Ancillary ancillary
    ) {
        if (request == null || existing == null) return;

        if (ancillary != null) existing.setAncillary(ancillary);
        if (request.getFlightId() != null) existing.setFlightId(request.getFlightId());
        if (request.getCabinClassId() != null) existing.setCabinClassId(request.getCabinClassId());
        if (request.getAvailable() != null) existing.setAvailable(request.getAvailable());
        if (request.getMaxQuantity() != null) existing.setMaxQuantity(request.getMaxQuantity());
        if (request.getPrice() != null) existing.setPrice(request.getPrice());
        if (request.getIncludedInFare() != null) existing.setIncludedInFare(request.getIncludedInFare());
    }
}