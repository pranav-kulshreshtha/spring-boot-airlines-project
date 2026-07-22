package com.msp.mapper;

import com.msp.models.Meal;
import com.msp.payloads.responses.MealResponse;

public class MealMapper {

    public static MealResponse toResponse(Meal meal) {
        if(meal == null)return null;

        return MealResponse.builder()
                .id(meal.getId())
                .code(meal.getCode())
                .name(meal.getName())
                .mealType(meal.getMealType())
                .dietaryRestrictions(meal.getDietaryRestrictions())
                .ingredients(meal.getIngredients())
                .imageUrl(meal.getImageUrl())
                .isAvailable(meal.getIsAvailable())
                .requiresAdvanceBooking(meal.getRequiresAdvanceBooking())
                .advanceBookingHours(meal.getAdvanceBookingHours())
                .displayOrder(meal.getDisplayOrder())
                .airlineId(meal.getAirlineId())
                .createdAt(meal.getCreatedAt())
                .updatedAt(meal.getUpdatedAt())
                .build();
    }

}
