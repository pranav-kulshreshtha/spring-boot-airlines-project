package com.msp.mappers;

import com.msp.models.Airport;
import com.msp.payloads.requests.AirportRequest;
import com.msp.payloads.responses.AirportResponse;

public class AirportMapper {

    public static Airport toEntity(AirportRequest request){
        if(request == null)return null;

        return Airport.builder()
                .name(request.getName())
                .iataCode(request.getIataCode())
//                .timeZone(request.getTimeZone())
                .address(request.getAddress())
                .geoCode(request.getGeoCode())
                .build();
    }

    public static AirportResponse toResponse(Airport airport) {
        if(airport == null)return null;

        return AirportResponse.builder()
                .id(airport.getId())
                .name(airport.getName())
                .detailedName(airport.getDetailedName())
                .iataCode(airport.getIataCode())
//                .timeZone(airport.getTimeZone())
                .address(airport.getAddress())
                .geoCode(airport.getGeoCode())
                .build();
    }

    public static Airport updateEntity(Airport airport, AirportRequest request) {

        if (request.getName() != null) {
            airport.setName(request.getName().trim());
        }

        if (request.getIataCode() != null) {
            airport.setIataCode(request.getIataCode().toUpperCase().trim());
        }

        if (request.getAddress() != null) {
            airport.setAddress(request.getAddress());
        }

        if (request.getGeoCode() != null) {
            airport.setGeoCode(request.getGeoCode());
        }

        return airport;
    }
}
