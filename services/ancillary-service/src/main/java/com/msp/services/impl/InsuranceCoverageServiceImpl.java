package com.msp.services.impl;

import com.msp.mapper.InsuranceCoverageMapper;
import com.msp.models.Ancillary;
import com.msp.models.InsuranceCoverage;
import com.msp.payloads.requests.InsuranceCoverageRequest;
import com.msp.payloads.responses.InsuranceCoverageResponse;
import com.msp.repositories.AncillaryRepository;
import com.msp.repositories.InsuranceCoverageRepository;
import com.msp.services.InsuranceCoverageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InsuranceCoverageServiceImpl implements InsuranceCoverageService {

    private final InsuranceCoverageRepository insuranceCoverageRepository;
    private final AncillaryRepository ancillaryRepository;

    @Override
    public InsuranceCoverageResponse createCoverage(InsuranceCoverageRequest request)
            throws Exception {

        Ancillary ancillary = ancillaryRepository.findById(request.getAncillaryId())
                .orElseThrow(() ->
                        new Exception("Ancillary with provided id not found!"));

        InsuranceCoverage insuranceCoverage = InsuranceCoverageMapper.toEntity(
                request, ancillary);
        InsuranceCoverage saved = insuranceCoverageRepository.save(insuranceCoverage);

        return InsuranceCoverageMapper.toResponse(saved);
    }

    @Override
    public InsuranceCoverageResponse getCoverageById(Long id) throws Exception {
        InsuranceCoverage coverage = insuranceCoverageRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Coverage with given id doesn't exist!"));

        return InsuranceCoverageMapper.toResponse(coverage);
    }

    @Override
    public List<InsuranceCoverageResponse> getCoverageByAncillaryId(Long ancillaryId) {
        return insuranceCoverageRepository.findByAncillaryId(ancillaryId)
                .stream()
                .map(InsuranceCoverageMapper::toResponse)
                .toList();
    }

    @Override
    public List<InsuranceCoverageResponse> getActiveCoverageByAncillaryId(Long ancillaryId) {
        return insuranceCoverageRepository.findByAncillaryIdAndActiveTrue(ancillaryId)
                .stream()
                .map(InsuranceCoverageMapper::toResponse)
                .toList();
    }

    @Override
    public List<InsuranceCoverageResponse> getAllCoverages() {
        return insuranceCoverageRepository.findAll()
                .stream()
                .map(InsuranceCoverageMapper::toResponse)
                .toList();
    }

    @Override
    public InsuranceCoverageResponse updateCoverage(Long id, InsuranceCoverageRequest request) throws Exception {
        InsuranceCoverage existing = insuranceCoverageRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Coverage with given id doesn't exist!"));

        Ancillary ancillary = null;
        if(request.getAncillaryId() != null) {
            ancillary = ancillaryRepository.findById(request.getAncillaryId())
                    .orElseThrow(() ->
                            new Exception("Ancillary with provided id not found!"));
        }

        InsuranceCoverageMapper.updateEntity(request, existing, ancillary);
        InsuranceCoverage updated = insuranceCoverageRepository.save(existing);

        return InsuranceCoverageMapper.toResponse(updated);
    }

    @Override
    public void deleteCoverage(Long id) throws Exception {
        InsuranceCoverage coverage = insuranceCoverageRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Coverage with given id doesn't exist!"));
        insuranceCoverageRepository.delete(coverage);
    }
}
