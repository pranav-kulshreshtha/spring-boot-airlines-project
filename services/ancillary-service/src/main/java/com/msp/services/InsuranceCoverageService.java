package com.msp.services;

import com.msp.payloads.responses.InsuranceCoverageResponse;
import com.msp.payloads.requests.InsuranceCoverageRequest;

import java.util.List;

public interface InsuranceCoverageService {

    InsuranceCoverageResponse createCoverage(InsuranceCoverageRequest request) throws Exception;

    InsuranceCoverageResponse getCoverageById(Long id) throws Exception;

    List<InsuranceCoverageResponse> getCoverageByAncillaryId(Long ancillaryId);

    List<InsuranceCoverageResponse> getActiveCoverageByAncillaryId(Long ancillaryId);

    List<InsuranceCoverageResponse> getAllCoverages();

    InsuranceCoverageResponse updateCoverage(Long id, InsuranceCoverageRequest request) throws Exception;

    void deleteCoverage(Long id) throws Exception;

}
