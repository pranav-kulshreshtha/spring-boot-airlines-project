package com.msp.services.impl;

import com.msp.mapper.FlightMealMapper;
import com.msp.models.FlightMeal;
import com.msp.models.Meal;
import com.msp.payloads.requests.FlightMealRequest;
import com.msp.payloads.responses.FlightMealResponse;
import com.msp.repositories.FlightMealRepository;
import com.msp.repositories.MealRepository;
import com.msp.services.FlightMealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightMealServiceImpl implements FlightMealService {

    private final FlightMealRepository flightMealRepository;
    private final MealRepository mealRepository;

    @Override
    public FlightMealResponse createFlightMeal(FlightMealRequest request) throws Exception {
        Meal meal = mealRepository.findById(request.getMealId())
                .orElseThrow(() -> new Exception("Meal with given id not found!"));

        if(flightMealRepository.existsByFlightIdAndMealId(
                request.getFlightId(),
                request.getMealId()
        )) {
            throw new Exception("Meal already exists for flight!");
        }

        FlightMeal flightMeal = FlightMeal.builder()
                .flightId(request.getFlightId())
                .meal(meal)
                .price(request.getPrice())
                .available(request.getAvailable())
                .displayOrder(request.getDisplayOrder()!=null
                        ? request.getDisplayOrder() : null)
                .build();
        FlightMeal saved = flightMealRepository.save(flightMeal);

        return FlightMealMapper.toResponse(saved);
    }

    @Override
    public FlightMealResponse getFlightMealById(Long id) throws Exception {
        FlightMeal flightMeal = flightMealRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Flight meal with given id not found!"));

        return FlightMealMapper.toResponse(flightMeal);
    }

    @Override
    public List<FlightMealResponse> getByFlightId(Long flightId) {
        return flightMealRepository.findByFlightId(flightId)
                .stream()
                .map(FlightMealMapper::toResponse)
                .toList();
    }

    @Override
    public List<FlightMealResponse> getAllByIds(List<Long> ids) {
        return flightMealRepository.findAllById(ids)
                .stream()
                .map(FlightMealMapper::toResponse)
                .toList();
    }

    @Override
    public FlightMealResponse updateFlightMeal(Long id, FlightMealRequest request)
            throws Exception {

        FlightMeal existing = flightMealRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Flight meal with given id not found!"));

        existing.setFlightId(request.getFlightId());

        Meal meal = null;
        if(request.getMealId()!=null) {
            meal = mealRepository.findById(request.getMealId())
                    .orElseThrow(() ->
                            new Exception("Meal with given id not found!"));
        }

        if(meal!=null) existing.setMeal(meal);
        if(request.getAvailable()!=null) existing.setAvailable(request.getAvailable());
        if(request.getPrice()!=null) existing.setPrice(request.getPrice());
        if(request.getDisplayOrder()!=null)
            existing.setDisplayOrder(request.getDisplayOrder());

        FlightMeal updated = flightMealRepository.save(existing);

        return FlightMealMapper.toResponse(updated);
    }

    @Override
    public FlightMealResponse updateFlightMealAvailability(Long id, Boolean availability)
            throws Exception {
        FlightMeal flightMeal = flightMealRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Flight meal with given id not found!"));

        flightMeal.setAvailable(availability);

        FlightMeal updated = flightMealRepository.save(flightMeal);

        return FlightMealMapper.toResponse(updated);
    }

    @Override
    public void deleteFlightMeal(Long id) throws Exception {
        FlightMeal flightMeal = flightMealRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Flight meal with given id not found!"));

        flightMealRepository.delete(flightMeal);
    }

    @Override
    public Double calculateMealPrice(List<Long> mealIds) {
        List<FlightMeal> meals = flightMealRepository.findAllById(mealIds);
        double price = 0.0;
        for(FlightMeal meal : meals) {
            price += meal.getPrice();
        }

        return price;
    }
}
