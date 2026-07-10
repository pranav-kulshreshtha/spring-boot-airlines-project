package com.msp.services.impl;

import com.msp.mappers.FareRulesMapper;
import com.msp.models.Fare;
import com.msp.models.FareRules;
import com.msp.payloads.requests.FareRulesRequest;
import com.msp.payloads.responses.FareRulesResponse;
import com.msp.repositories.FareRepository;
import com.msp.repositories.FareRulesRepository;
import com.msp.services.FareRulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FareRulesServiceImpl implements FareRulesService {
    private final FareRepository fareRepository;
    private final FareRulesRepository fareRulesRepository;

    @Override
    public FareRulesResponse createFareRules(FareRulesRequest request) throws Exception {
        Fare fare = fareRepository.findById(request.getFareId())
                .orElseThrow(() -> new Exception("Fare with given id does not exist!"));

        if(fareRulesRepository.existsByFareId(fare.getId())) {
            throw new Exception("Fare already exists!");
        }

        FareRules fareRules = FareRulesMapper.toEntity(request, fare);
        FareRules saved = fareRulesRepository.save(fareRules);

        return FareRulesMapper.toResponse(saved);
    }

    @Override
    public FareRulesResponse getFareRulesById(Long id) throws Exception {
        FareRules fareRules = fareRulesRepository.findById(id)
                .orElseThrow(() -> new Exception("Fare rules with given id does not exist!"));
        return FareRulesMapper.toResponse(fareRules);
    }

    @Override
    public FareRulesResponse getFareRulesByFareId(Long fareId) {
        FareRules fareRules = fareRulesRepository.findByFareId(fareId);
        return FareRulesMapper.toResponse(fareRules);
    }

    @Override
    public List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId) {
        List<FareRules> fareRulesList = fareRulesRepository.findByAirlineId(
          airlineId
        );

        return fareRulesList.stream()
                .map(FareRulesMapper::toResponse)
                .toList();
    }

    @Override
    public FareRulesResponse updateFareRules(Long id, FareRulesRequest request) throws Exception {
        FareRules existing = fareRulesRepository.findById(id)
                .orElseThrow(() -> new Exception("Fare rules with given id does not exist!"));
        FareRulesMapper.updateEntity(request, existing);
        FareRules updated = fareRulesRepository.save(existing);
        return FareRulesMapper.toResponse(updated);
    }

    @Override
    public void deleteFareRules(Long id) throws Exception {
        FareRules fareRules = fareRulesRepository.findById(id)
                .orElseThrow(() -> new Exception("Fare rules with given id does not exist!"));
        fareRulesRepository.delete(fareRules);
    }
}
