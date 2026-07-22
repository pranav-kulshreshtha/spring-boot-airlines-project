package com.msp.mapper;

import com.msp.models.Ancillary;
import com.msp.models.InsuranceCoverage;
import com.msp.payloads.requests.InsuranceCoverageRequest;
import com.msp.payloads.responses.InsuranceCoverageResponse;

public class InsuranceCoverageMapper {

    public static InsuranceCoverage toEntity(
            InsuranceCoverageRequest request,
            Ancillary ancillary
    ) {
        if(request == null) return null;

        return InsuranceCoverage.builder()
                .ancillary(ancillary)
                .coverageType(request.getCoverageType())
                .name(request.getName())
                .description(request.getDescription())
                .coverageAmount(request.getCoverageAmount())
                .isFlat(request.getIsFlat() != null ? request.getIsFlat() : true)
                .claimCondition(request.getClaimCondition())
                .emergencyContact(request.getEmergencyContact())
                .displayOrder(request.getDisplayOrder())
                .active(request.getActive() != null ? request.getActive() : true)
                .build();
    }

    public static InsuranceCoverageResponse toResponse(InsuranceCoverage insuranceCoverage) {
        if(insuranceCoverage == null) return null;

        return InsuranceCoverageResponse.builder()
                .id(insuranceCoverage.getId())
                .ancillaryId(insuranceCoverage.getAncillary() != null ?
                        insuranceCoverage.getAncillary().getId() : null)
                .ancillaryName(insuranceCoverage.getAncillary() != null ?
                        insuranceCoverage.getAncillary().getName() : null)
                .name(insuranceCoverage.getName())
                .description(insuranceCoverage.getDescription())
                .coverageType(insuranceCoverage.getCoverageType())
                .coverageAmount(insuranceCoverage.getCoverageAmount())
                .isFlat(insuranceCoverage.isFlat())
                .claimCondition(insuranceCoverage.getClaimCondition())
                .emergencyContact(insuranceCoverage.getEmergencyContact())
                .displayOrder(insuranceCoverage.getDisplayOrder())
                .active(insuranceCoverage.getActive())
                .build();
    }

    public static void updateEntity(
            InsuranceCoverageRequest request,
            InsuranceCoverage existing,
            Ancillary ancillary
    ) {
        if(request == null || existing == null) return;

        if(ancillary != null)
            existing.setAncillary(ancillary);
        if(request.getCoverageType() != null)
            existing.setCoverageType(request.getCoverageType());
        if(request.getName() != null)
            existing.setName(request.getName());
        if(request.getDescription() != null)
            existing.setDescription(request.getDescription());
        if(request.getCoverageAmount() != null)
            existing.setCoverageAmount(request.getCoverageAmount());
        if(request.getIsFlat() != null)
            existing.setFlat(request.getIsFlat());
        if(request.getClaimCondition() != null)
            existing.setClaimCondition(request.getClaimCondition());
        if(request.getEmergencyContact() != null)
            existing.setEmergencyContact(request.getEmergencyContact());
        if(request.getDisplayOrder() != null)
            existing.setDisplayOrder(request.getDisplayOrder());
        if(request.getActive() != null)
            existing.setActive(request.getActive());
    }
}
