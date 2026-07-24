package com.msp.controllers;

import com.msp.payloads.requests.MealRequest;
import com.msp.payloads.responses.ApiResponse;
import com.msp.payloads.responses.MealResponse;
import com.msp.services.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;

    @PostMapping
    public ResponseEntity<MealResponse> createMeal(
            @Valid @RequestBody MealRequest request,
            @RequestHeader("X-Airline-Id") Long airlineId
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mealService.createMeal(airlineId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealResponse> getMealById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(mealService.getMealById(id));
    }

    @GetMapping("/airline")
    public ResponseEntity<List<MealResponse>> getMealsByAirlineId(
            @RequestHeader("X-Airline-Id") Long airlineId
    ) {
        return ResponseEntity.ok(mealService.getMealByAirlineId(airlineId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealResponse> updateMeal(
            @RequestHeader("X-Airline-Id") Long airlineId,
            @PathVariable Long id,
            @RequestBody MealRequest request
    ) throws Exception {
        return ResponseEntity.ok(mealService.updateMeal(airlineId, id, request));
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<MealResponse> updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean availability
    ) throws Exception {
        return ResponseEntity.ok(mealService.updateAvailability(id, availability));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMeal(
            @PathVariable Long id
    ) throws Exception {
        mealService.deleteMeal(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Meal deleted successfully!");

        return ResponseEntity.ok(apiResponse);
    }
}