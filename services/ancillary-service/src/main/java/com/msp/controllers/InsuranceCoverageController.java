package com.msp.controllers;

import com.msp.payloads.requests.InsuranceCoverageRequest;
import com.msp.payloads.responses.ApiResponse;
import com.msp.payloads.responses.InsuranceCoverageResponse;
import com.msp.services.InsuranceCoverageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/insurance-coverages")
public class InsuranceCoverageController {

    private final InsuranceCoverageService insuranceCoverageService;

    @PostMapping
    public ResponseEntity<InsuranceCoverageResponse> createInsuranceCoverage(
            @Valid @RequestBody InsuranceCoverageRequest request
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(insuranceCoverageService.createCoverage(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceCoverageResponse> getCoverageById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(insuranceCoverageService.getCoverageById(id));
    }

    @GetMapping("/ancillary/{ancillaryId}")
    public ResponseEntity<List<InsuranceCoverageResponse>> getCoverageByAncillaryId(
            @PathVariable Long ancillaryId
    ) {
        return ResponseEntity.ok(
                insuranceCoverageService.getCoverageByAncillaryId(ancillaryId)
        );
    }

    @GetMapping("/ancillary/{ancillaryId}/active")
    public ResponseEntity<List<InsuranceCoverageResponse>> getActiveCoverageByAncillaryId(
            @PathVariable Long ancillaryId
    ) {
        return ResponseEntity.ok(
                insuranceCoverageService.getActiveCoverageByAncillaryId(ancillaryId)
        );
    }

    @GetMapping
    public ResponseEntity<List<InsuranceCoverageResponse>> getAllCoverages() {
        return ResponseEntity.ok(
                insuranceCoverageService.getAllCoverages()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsuranceCoverageResponse> updateCoverage(
            @PathVariable Long id,
            @RequestBody InsuranceCoverageRequest request
    ) throws Exception {
        return ResponseEntity.ok(
                insuranceCoverageService.updateCoverage(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCoverage(
            @PathVariable Long id
    ) throws Exception {

        insuranceCoverageService.deleteCoverage(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Insurance coverage deleted successfully!");

        return ResponseEntity.ok(apiResponse);
    }
}
