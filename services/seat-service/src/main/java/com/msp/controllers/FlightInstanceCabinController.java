package com.msp.controllers;

import com.msp.payloads.requests.FlightInstanceCabinRequest;
import com.msp.payloads.responses.ApiResponse;
import com.msp.payloads.responses.FlightInstanceCabinResponse;
import com.msp.services.FlightInstanceCabinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/flight-instance-cabins")
public class FlightInstanceCabinController {

    private final FlightInstanceCabinService flightInstanceCabinService;

    @PostMapping
    public ResponseEntity<FlightInstanceCabinResponse> createFlightInstanceCabin (
            @Valid @RequestBody FlightInstanceCabinRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flightInstanceCabinService.createFlightInstanceCabin(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightInstanceCabinResponse> getFlightInstanceCabinById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(
                flightInstanceCabinService.getFlightInstanceCabinById(id));
    }

    @GetMapping("/flight-instance/{flightInstanceId}/cabin-class/{cabinClassId}")
    public ResponseEntity<FlightInstanceCabinResponse>
        getByFlightInstanceIdAndCabinClassId(
            @PathVariable Long flightInstanceId,
            @PathVariable Long cabinClassId
    ) throws Exception {
        return ResponseEntity.ok(
                flightInstanceCabinService.getByFlightInstanceIdAndCabinClassId(
                        flightInstanceId, cabinClassId));
    }

    @GetMapping("/flight-instance/{flightInstanceId}")
    public ResponseEntity<Page<FlightInstanceCabinResponse>> getByFlightInstanceId(
            @PathVariable Long flightInstanceId, Pageable pageable) {
        return ResponseEntity.ok(
                    flightInstanceCabinService.getByFlightInstanceId(
                            flightInstanceId, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightInstanceCabinResponse> updateFlightInstanceCabin(
            @PathVariable Long id,
            @RequestBody FlightInstanceCabinRequest request
    ) throws Exception {
        return ResponseEntity.ok(
                flightInstanceCabinService.updateFlightInstanceCabin(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFlightInstanceCabin(
            @PathVariable Long id) throws Exception {

        ApiResponse apiResponse = new ApiResponse();
        flightInstanceCabinService.deleteFlightInstanceCabin(id);

        apiResponse.setMessage("Flight instance cabin deleted!");
        return ResponseEntity.ok(apiResponse);
    }


}
