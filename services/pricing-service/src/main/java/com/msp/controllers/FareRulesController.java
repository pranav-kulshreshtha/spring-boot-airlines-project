package com.msp.controllers;

import com.msp.payloads.requests.FareRulesRequest;
import com.msp.payloads.responses.ApiResponse;
import com.msp.payloads.responses.FareRulesResponse;
import com.msp.repositories.FareRulesRepository;
import com.msp.services.FareRulesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fare-rules")
public class FareRulesController {

    private final FareRulesService fareRulesService;

    @PostMapping
    public ResponseEntity<FareRulesResponse> createFareRule(
            @Valid @RequestBody FareRulesRequest fareRulesRequest
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                fareRulesService.createFareRules(fareRulesRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FareRulesResponse> getFareRulesById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(fareRulesService.getFareRulesById(id));
    }

    @GetMapping("/fare/{fareId}")
    public ResponseEntity<FareRulesResponse> getFareRulesByFareId(
            @PathVariable Long fareId
    ) throws Exception {
        return ResponseEntity.ok(fareRulesService.getFareRulesByFareId(fareId));
    }

    @GetMapping("/airlineId/{airlineId}")
    public ResponseEntity<List<FareRulesResponse>> getFareRulesByAirlineId(
            @PathVariable Long airlineId
    ) throws Exception {
        return ResponseEntity.ok(fareRulesService.getFareRulesByAirlineId(airlineId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FareRulesResponse> updateFareRules(
            @PathVariable Long id,
            @RequestBody FareRulesRequest request
    ) throws Exception {
        return ResponseEntity.ok(fareRulesService.updateFareRules(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFareRules(@PathVariable Long id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        fareRulesService.deleteFareRules(id);
        apiResponse.setMessage("Fare rules were deleted successfully!");
        return ResponseEntity.ok(apiResponse);
    }
}
