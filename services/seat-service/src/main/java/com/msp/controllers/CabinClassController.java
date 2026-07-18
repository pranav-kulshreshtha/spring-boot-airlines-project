package com.msp.controllers;

import com.msp.enums.CabinClassType;
import com.msp.payloads.requests.CabinClassRequest;
import com.msp.payloads.responses.ApiResponse;
import com.msp.payloads.responses.CabinClassResponse;
import com.msp.services.CabinClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cabin-classes")
public class CabinClassController {

    private final CabinClassService cabinClassService;

    @PostMapping
    public ResponseEntity<CabinClassResponse> createCabinClass(
            @Valid @RequestBody CabinClassRequest cabinClassRequest
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cabinClassService.createCabinClass(cabinClassRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabinClassResponse> getCabinClassById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(cabinClassService.getCabinClassById(id));
    }

    @GetMapping("/aircraft/{id}/name/{cabinClass}")
    public ResponseEntity<CabinClassResponse> getCabinClassByAircraftIdAndName(
            @PathVariable CabinClassType cabinClass,
            @PathVariable Long id
            ) {
        return ResponseEntity.ok(
                cabinClassService.getByAircraftIdAndName(id, cabinClass));
    }

    @GetMapping("/aircraftId/{aircraftId}")
    public ResponseEntity<List<CabinClassResponse>> getCabinClassesByAircraftId(
        @PathVariable Long aircraftId
    ) {
        return ResponseEntity.ok(
                cabinClassService.getCabinClassByAircraftId(aircraftId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CabinClassResponse> updateCabinClass(
            @PathVariable Long id,
            @RequestBody CabinClassRequest cabinClassRequest
    ) throws Exception {
        return ResponseEntity.ok(
                cabinClassService.updateCabinClass(id, cabinClassRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCabinClass(@PathVariable Long id)
            throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        cabinClassService.deleteCabinClass(id);
        apiResponse.setMessage("Cabin Class ID updated successfully!");
        return ResponseEntity.ok(apiResponse);
    }
}
