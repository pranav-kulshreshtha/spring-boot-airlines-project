package com.msp.controllers;

import com.msp.payloads.requests.BaggagePolicyRequest;
import com.msp.payloads.responses.ApiResponse;
import com.msp.payloads.responses.BaggagePolicyResponse;
import com.msp.services.BaggagePolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/baggage-policies")
public class BaggagePolicyController {

    private final BaggagePolicyService baggagePolicyService;

    @PostMapping
    public ResponseEntity<BaggagePolicyResponse> createBaggagePolicy(
            @Valid @RequestBody BaggagePolicyRequest baggagePolicyRequest
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                baggagePolicyService.createBaggagePolicy(baggagePolicyRequest)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaggagePolicyResponse> getBaggagePolicyById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(baggagePolicyService.getBaggagePolicyById(id));
    }

    @GetMapping("/{fareId}")
    public ResponseEntity<BaggagePolicyResponse> getBaggagePolicyByFareId(
            @PathVariable Long fareId
    ) throws Exception {
        return ResponseEntity.ok(baggagePolicyService.getBaggagePolicyByFareId(fareId));
    }

    @GetMapping("/airline/{airlineId}")
    public ResponseEntity<List<BaggagePolicyResponse>> getBaggagePoliciesByAirlineId(
            @PathVariable Long airlineId
    ) {
        return ResponseEntity.ok(
                baggagePolicyService.getBaggagePoliciesByAirlineId(airlineId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaggagePolicyResponse> updateBaggagePolicy(
            @PathVariable Long id,
            @RequestBody BaggagePolicyRequest request
    ) throws Exception {

        return ResponseEntity.ok(
                baggagePolicyService.updateBaggagePolicy(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBaggagePolicy(
            @PathVariable Long id
    ) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        baggagePolicyService.deleteBaggagePolicy(id);

        apiResponse.setMessage("Baggage policy deleted successfully!");
        return ResponseEntity.ok(apiResponse);
    }
}
