package com.msp.mappers;

import com.msp.models.BaggagePolicy;
import com.msp.models.Fare;
import com.msp.payloads.requests.BaggagePolicyRequest;
import com.msp.payloads.responses.BaggagePolicyResponse;

public class BaggagePolicyMapper {
    public static BaggagePolicy toEntity(
            BaggagePolicyRequest request,
            Fare fare
    ) {
        if(request == null || fare == null)return null;

        return BaggagePolicy.builder()
                .name(request.getName())
                .description(request.getDescription())
                .fare(fare)
                .cabinBaggageMaxWeight(request.getCabinBaggageMaxWeight())
                .cabinBaggagePieces(request.getCabinBaggagePieces())
                .cabinBaggageWeightPerPiece(request.getCabinBaggageWeightPerPiece())
                .cabinBaggageMaxDimension(request.getCabinBaggageMaxDimension())
                .checkinBaggageMaxWeight(request.getCheckinBaggageMaxWeight())
                .checkinBaggagePieces(request.getCheckinBaggagePieces())
                .checkinBaggageWeightPerPiece(request.getCheckinBaggageWeightPerPiece())
                .freeCheckedBaggageAllowance(request.getFreeCheckedBaggageAllowance())
                .priorityBaggage(request.getPriorityBaggage()!=null ?
                        request.getPriorityBaggage() : false)
                .extraBaggageAllowance(request.getExtraBaggageAllowance()!=null ?
                        request.getExtraBaggageAllowance() : false)
                .build();
    }

    public static BaggagePolicyResponse toResponse(BaggagePolicy policy) {
        if(policy == null)return null;

        return BaggagePolicyResponse.builder()
                .id(policy.getId())
                .name(policy.getName())
                .description(policy.getDescription())
                .fareId(policy.getFare().getId())
                .cabinBaggageMaxWeight(policy.getCabinBaggageMaxWeight())
                .cabinBaggageWeightPerPiece(policy.getCabinBaggageWeightPerPiece())
                .cabinBaggagePieces(policy.getCabinBaggagePieces())
                .cabinBaggageMaxDimension(policy.getCabinBaggageMaxDimension())
                .checkinBaggagePieces(policy.getCheckinBaggagePieces())
                .checkinBaggageMaxWeight(policy.getCheckinBaggageMaxWeight())
                .checkinBaggageWeightPerPiece(policy.getCheckinBaggageWeightPerPiece())
                .freeCheckedBaggageAllowance(policy.getFreeCheckedBaggageAllowance())
                .extraBaggageAllowance(policy.getExtraBaggageAllowance()!=null ?
                        policy.getExtraBaggageAllowance() : false)
                .priorityBaggage(policy.getPriorityBaggage()!=null ?
                        policy.getPriorityBaggage() : false)
                .createdAt(policy.getCreatedAt())
                .updatedAt(policy.getUpdatedAt())
                .build();
    }

    public static void updateEntity(
            BaggagePolicyRequest request,
            BaggagePolicy existing) {

        if(request == null || existing == null) return;

        if(request.getName() != null) existing.setName(request.getName());
        if(request.getDescription() != null) existing.setDescription(request.getDescription());
        if(request.getCabinBaggageMaxWeight() != null) existing.setCabinBaggageMaxWeight(request.getCabinBaggageMaxWeight());
        if(request.getCabinBaggagePieces() != null) existing.setCabinBaggagePieces(request.getCabinBaggagePieces());
        if(request.getCabinBaggageWeightPerPiece() != null) existing.setCabinBaggageWeightPerPiece(request.getCabinBaggageWeightPerPiece());
        if(request.getCabinBaggageMaxDimension() != null) existing.setCabinBaggageMaxDimension(request.getCabinBaggageMaxDimension());
        if(request.getCheckinBaggageMaxWeight() != null) existing.setCheckinBaggageMaxWeight(request.getCheckinBaggageMaxWeight());
        if(request.getCheckinBaggagePieces() != null) existing.setCheckinBaggagePieces(request.getCheckinBaggagePieces());
        if(request.getCheckinBaggageWeightPerPiece() != null) existing.setCheckinBaggageWeightPerPiece(request.getCheckinBaggageWeightPerPiece());
        if(request.getFreeCheckedBaggageAllowance() != null) existing.setFreeCheckedBaggageAllowance(request.getFreeCheckedBaggageAllowance());
        if(request.getPriorityBaggage() != null) existing.setPriorityBaggage(request.getPriorityBaggage());
        if(request.getExtraBaggageAllowance() != null) existing.setExtraBaggageAllowance(request.getExtraBaggageAllowance());
    }
}
