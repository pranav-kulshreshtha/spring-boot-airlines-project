package com.msp.repositories;

import com.msp.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByIataCode(String iata);

    List<Airport> findByCityId(Long cityId);
}
