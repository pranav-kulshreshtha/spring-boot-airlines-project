package com.msp.mappers;

import com.msp.embaddables.*;
import com.msp.models.Fare;
import com.msp.payloads.requests.FareRequest;
import com.msp.payloads.responses.FareResponse;

public class FareMapper {

    public static Fare toEntity(FareRequest fareRequest) {
        if (fareRequest == null) return null;

        Double calculatedPrice = fareRequest.getCurrentPrice();
        if (calculatedPrice == null) {
            calculatedPrice = fareRequest.getBaseFare() + fareRequest.getTaxesAndFees()
                    + fareRequest.getAirlineFees();
        }

        SeatBenefits seatBenefits = SeatBenefits.builder()
                .extraSeatSpace(bool(fareRequest.getExtraSeatSpace()))
                .guaranteedSeatsTogether(bool(fareRequest.getGuaranteedSeatsTogether()))
                .preferredSeatChoice(bool(fareRequest.getPreferredSeatChoice()))
                .advanceSeatSelection(bool(fareRequest.getAdvanceSeatSelection()))
                .build();

        BoardingBenefits boardingBenefits = BoardingBenefits.builder()
                .priorityBoarding(bool(fareRequest.getPriorityBoarding()))
                .priorityCheckin(bool(fareRequest.getPriorityCheckin()))
                .fastTrackSecurity(bool(fareRequest.getFastTrackSecurity()))
                .build();

        FlexibilityBenefits flexibilityBenefits = FlexibilityBenefits.builder()
                .freeDateChange(bool(fareRequest.getFreeDateChange()))
                .partialRefund(bool(fareRequest.getPartialRefund()))
                .fullRefund(bool(fareRequest.getFullRefund()))
                .build();

        InFlightBenefits inFlightBenefits = InFlightBenefits.builder()
                .complimentaryMeals(bool(fareRequest.getComplimentaryMeals()))
                .premiumMealChoice(bool(fareRequest.getPremiumMealChoice()))
                .inFlightInternet(bool(fareRequest.getInFlightInternet()))
                .inFlightEntertainment(bool(fareRequest.getInFlightEntertainment()))
                .complimentaryBeverages(bool(fareRequest.getComplimentaryBeverages()))
                .build();

        PremiumServiceBenefits premiumServiceBenefits = PremiumServiceBenefits.builder()
                .loungeAccess(bool(fareRequest.getLoungeAccess()))
                .airportTransfer(bool(fareRequest.getAirportTransfer()))
                .build();

        return Fare.builder()
                .name(fareRequest.getName())
                .fareLabel(fareRequest.getFareLabel())
                .flightId(fareRequest.getFlightId())
                .baseFare(fareRequest.getBaseFare())
                .airlineFees(fareRequest.getAirlineFees())
                .taxesAndFees(fareRequest.getTaxesAndFees())
                .currentPrice(calculatedPrice)
                .cabinClassId(fareRequest.getCabinClassId())
                .rbdCode(fareRequest.getRbdCode())
                .seatBenefits(seatBenefits)
                .boardingBenefits(boardingBenefits)
                .flexibilityBenefits(flexibilityBenefits)
                .inFlightBenefits(inFlightBenefits)
                .premiumServiceBenefits(premiumServiceBenefits)
                .build();
        }

    public static FareResponse toResponse(Fare fare) {
        if (fare == null) {
            return null;
        }

        return FareResponse.builder()
                .id(fare.getId())
                .name(fare.getName())
                .rbdCode(fare.getRbdCode())
                .flightId(fare.getFlightId())
                .cabinClassId(fare.getCabinClassId())
                .cabinClass(fare.getCabinClass())
                .baseFare(fare.getBaseFare())
                .taxesAndFees(fare.getTaxesAndFees())
                .airlineFees(fare.getAirlineFees())
                .currentPrice(fare.getCurrentPrice())
                .totalPrice(fare.totalPrice())
                .fareLabel(fare.getFareLabel())
//                .fareRulesId(fare.getFareRules() != null ? fare.getFareRules() : null)
                .extraSeatSpace(fare.getSeatBenefits()!=null ?
                        fare.getSeatBenefits().isExtraSeatSpace() : null)
                .preferredSeatChoice(fare.getSeatBenefits() != null ?
                        fare.getSeatBenefits().isPreferredSeatChoice() : null)
                .advanceSeatSelection(fare.getSeatBenefits() != null ?
                        fare.getSeatBenefits().isAdvanceSeatSelection() : null)
                .guaranteedSeatsTogether(fare.getSeatBenefits() != null ?
                        fare.getSeatBenefits().isGuaranteedSeatsTogether() : null)

                // Boarding Benefits
                .priorityBoarding(fare.getBoardingBenefits() != null ?
                        fare.getBoardingBenefits().getPriorityBoarding() : null)
                .priorityCheckin(fare.getBoardingBenefits() != null ?
                        fare.getBoardingBenefits().getPriorityCheckin() : null)
                .fastTrackSecurity(fare.getBoardingBenefits() != null ?
                        fare.getBoardingBenefits().getFastTrackSecurity() : null)

                // In-flight Benefits
                .complimentaryMeals(fare.getInFlightBenefits() != null ?
                        fare.getInFlightBenefits().getComplimentaryMeals() : null)
                .premiumMealChoice(fare.getInFlightBenefits() != null ?
                        fare.getInFlightBenefits().getPremiumMealChoice() : null)
                .inFlightInternet(fare.getInFlightBenefits() != null ?
                        fare.getInFlightBenefits().getInFlightInternet() : null)
                .inFlightEntertainment(fare.getInFlightBenefits() != null ?
                        fare.getInFlightBenefits().getInFlightEntertainment() : null)
                .complimentaryBeverages(fare.getInFlightBenefits() != null ?
                        fare.getInFlightBenefits().getComplimentaryBeverages() : null)

                // Flexibility Benefits
                .freeDateChange(fare.getFlexibilityBenefits() != null ?
                        fare.getFlexibilityBenefits().getFreeDateChange() : null)
                .partialRefund(fare.getFlexibilityBenefits() != null ?
                        fare.getFlexibilityBenefits().getPartialRefund() : null)
                .fullRefund(fare.getFlexibilityBenefits() != null ?
                        fare.getFlexibilityBenefits().getFullRefund() : null)

                .loungeAccess(fare.getPremiumServiceBenefits() != null ?
                        fare.getPremiumServiceBenefits().getLoungeAccess() : null)
                .airportTransfer(fare.getPremiumServiceBenefits() != null ?
                        fare.getPremiumServiceBenefits().getAirportTransfer() : null)
                // todo : watch fare rules/baggage policy
//                .fareRules(fare.getFareRules() != null ?
//                        FareRulesMapper.toResponse(fare.getFareRules()) : null)
//                .baggagePolicy(fare.getBaggagePolicy() != null ?
//                        BaggagePolicyMapper.toResponse(fare.getBaggagePolicy()) : null)
                .createdAt(fare.getCreatedAt())
                .updatedAt(fare.getUpdatedAt())
                .build();
    }

    public static void updateEntity(FareRequest request, Fare existing) {
        if(request == null || existing == null) return;

        if(request.getName() != null) existing.setName(request.getName());
        if(request.getRbdCode() != null) existing.setRbdCode(request.getRbdCode());
        if(request.getFlightId() != null) existing.setFlightId(request.getFlightId());
        if(request.getCabinClassId() != null) existing.setCabinClassId(request.getCabinClassId());

        if(request.getBaseFare() != null) existing.setBaseFare(request.getBaseFare());
        if(request.getTaxesAndFees() != null) existing.setTaxesAndFees(request.getTaxesAndFees());
        if(request.getAirlineFees() != null) existing.setAirlineFees(request.getAirlineFees());
        if(request.getCurrentPrice() != null) existing.setCurrentPrice(request.getCurrentPrice());
        if(request.getFareLabel() != null) existing.setFareLabel(request.getFareLabel());

        // Update embedded benefits
        SeatBenefits sb = existing.getSeatBenefits();
        if(request.getExtraSeatSpace() != null) sb.setExtraSeatSpace(request.getExtraSeatSpace());
        if(request.getPreferredSeatChoice() != null) sb.setPreferredSeatChoice(request.getPreferredSeatChoice());
        if(request.getAdvanceSeatSelection() != null) sb.setAdvanceSeatSelection(request.getAdvanceSeatSelection());
        if(request.getGuaranteedSeatsTogether() != null) sb.setGuaranteedSeatsTogether(request.getGuaranteedSeatsTogether());

        BoardingBenefits bb = existing.getBoardingBenefits();
        if(request.getPriorityBoarding() != null) bb.setPriorityBoarding(request.getPriorityBoarding());
        if(request.getPriorityCheckin() != null) bb.setPriorityCheckin(request.getPriorityCheckin());
        if(request.getFastTrackSecurity() != null) bb.setFastTrackSecurity(request.getFastTrackSecurity());

        InFlightBenefits ifb = existing.getInFlightBenefits();
        if(request.getComplimentaryMeals() != null) ifb.setComplimentaryMeals(request.getComplimentaryMeals());
        if(request.getPremiumMealChoice() != null) ifb.setPremiumMealChoice(request.getPremiumMealChoice());
        if(request.getInFlightInternet() != null) ifb.setInFlightInternet(request.getInFlightInternet());
        if(request.getInFlightEntertainment() != null) ifb.setInFlightEntertainment(request.getInFlightEntertainment());
        if(request.getComplimentaryBeverages() != null) ifb.setComplimentaryBeverages(request.getComplimentaryBeverages());

        FlexibilityBenefits fb = existing.getFlexibilityBenefits();
        if(request.getFreeDateChange() != null) fb.setFreeDateChange(request.getFreeDateChange());
        if(request.getPartialRefund() != null) fb.setPartialRefund(request.getPartialRefund());
        if(request.getFullRefund() != null) fb.setFullRefund(request.getFullRefund());

        PremiumServiceBenefits psb = existing.getPremiumServiceBenefits();
        if(request.getLoungeAccess() != null) psb.setLoungeAccess(request.getLoungeAccess());
        if(request.getAirportTransfer() != null) psb.setAirportTransfer(request.getAirportTransfer());
    }

    private static boolean bool(Boolean val) {
        return val != null ? val : false;
    }
}
