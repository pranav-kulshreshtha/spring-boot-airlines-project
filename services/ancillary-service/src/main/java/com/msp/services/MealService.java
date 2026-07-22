package com.msp.services;

import com.msp.payloads.requests.MealRequest;
import com.msp.payloads.responses.MealResponse;
import java.util.List;

public interface MealService {

    MealResponse createMeal(Long airlineId, MealRequest request) throws Exception;

    MealResponse getMealById(Long id) throws Exception;

    List<MealResponse> getMealByAirlineId(Long airlineId);

    MealResponse updateMeal(Long airlineId, Long id, MealRequest request)
            throws Exception;

    MealResponse updateAvailability(Long id, boolean availability) throws Exception;

    void deleteMeal(Long id) throws Exception;

}
