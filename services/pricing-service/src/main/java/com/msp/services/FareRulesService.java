package com.msp.services;

import com.msp.payloads.requests.FareRulesRequest;
import com.msp.payloads.responses.FareRulesResponse;
import java.util.List;

public interface FareRulesService {
    FareRulesResponse createFareRules(FareRulesRequest request) throws Exception;
    FareRulesResponse getFareRulesById(Long id) throws Exception;
    FareRulesResponse getFareRulesByFareId(Long fareId);
    List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId);
    FareRulesResponse updateFareRules(Long id, FareRulesRequest request) throws Exception;
    void deleteFareRules(Long id) throws Exception;
}
