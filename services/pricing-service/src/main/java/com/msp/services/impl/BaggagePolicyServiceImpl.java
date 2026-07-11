package com.msp.services.impl;

import com.msp.mappers.BaggagePolicyMapper;
import com.msp.models.BaggagePolicy;
import com.msp.models.Fare;
import com.msp.payloads.requests.BaggagePolicyRequest;
import com.msp.payloads.responses.BaggagePolicyResponse;
import com.msp.repositories.BaggagePolicyRepository;
import com.msp.repositories.FareRepository;
import com.msp.services.BaggagePolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BaggagePolicyServiceImpl implements BaggagePolicyService {

    private final FareRepository fareRepository;
    private final BaggagePolicyRepository baggagePolicyRepository;

    @Override
    public BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest request) throws Exception {
        Fare fare = fareRepository.findById(request.getFareId())
                .orElseThrow(() -> new Exception("Fare not found with given id!"));

        if(baggagePolicyRepository.existsByFareId(fare.getId())){
            throw new Exception("Baggage policy for the given fare already exists!");
        }

        BaggagePolicy policy = BaggagePolicyMapper.toEntity(request, fare);
        BaggagePolicy saved = baggagePolicyRepository.save(policy);

        return BaggagePolicyMapper.toResponse(saved);
    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyById(Long id) throws Exception {
        BaggagePolicy policy = baggagePolicyRepository.findById(id)
                .orElseThrow(() -> new Exception("Policy with given id doesn't exist!"));
        return BaggagePolicyMapper.toResponse(policy);
    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId) {
        BaggagePolicy policy = baggagePolicyRepository.findByFareId(fareId);
        return BaggagePolicyMapper.toResponse(policy);
    }

    @Override
    public List<BaggagePolicyResponse> getBaggagePoliciesByAirlineId(Long airlineId) {
        List<BaggagePolicy> policies = baggagePolicyRepository.findByAirlineId(airlineId);
        return policies.stream()
                .map(BaggagePolicyMapper::toResponse)
                .toList();
    }

    @Override
    public BaggagePolicyResponse updateBaggagePolicy(Long id, BaggagePolicyRequest request) throws Exception {
        BaggagePolicy existing = baggagePolicyRepository.findById(id)
                .orElseThrow(() -> new Exception("Policy with given id doesn't exist!"));

        BaggagePolicyMapper.updateEntity(request, existing);
        BaggagePolicy updated = baggagePolicyRepository.save(existing);

        return BaggagePolicyMapper.toResponse(updated);
    }

    @Override
    public void deleteBaggagePolicy(Long id) throws Exception {
        BaggagePolicy policy = baggagePolicyRepository.findById(id)
                .orElseThrow(() -> new Exception("Policy with given id doesn't exist!"));
        baggagePolicyRepository.delete(policy);
    }
}
