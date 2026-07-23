package com.msp.services.impl;

import com.msp.enums.AncillaryType;
import com.msp.mapper.FlightCabinAncillaryMapper;
import com.msp.mapper.InsuranceCoverageMapper;
import com.msp.models.Ancillary;
import com.msp.models.FlightCabinAncillary;
import com.msp.models.InsuranceCoverage;
import com.msp.payloads.requests.FlightCabinAncillaryRequest;
import com.msp.payloads.responses.FlightCabinAncillaryResponse;
import com.msp.payloads.responses.InsuranceCoverageResponse;
import com.msp.repositories.AncillaryRepository;
import com.msp.repositories.FlightCabinAncillaryRepository;
import com.msp.repositories.InsuranceCoverageRepository;
import com.msp.services.FlightCabinAncillaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightCabinAncillaryServiceImpl implements FlightCabinAncillaryService {

    private final FlightCabinAncillaryRepository flightCabinAncillaryRepository;
    private final AncillaryRepository ancillaryRepository;
    private final InsuranceCoverageRepository insuranceCoverageRepository;

    @Override
    public FlightCabinAncillaryResponse createFlightCabinAncillary(FlightCabinAncillaryRequest request) throws Exception {
        Ancillary ancillary = ancillaryRepository.findById(request.getAncillaryId())
                .orElseThrow(() -> new Exception("Ancillary with given id not found!"));

        FlightCabinAncillary fcAncillary = FlightCabinAncillaryMapper.toEntity(
                request, ancillary);
        FlightCabinAncillary saved = flightCabinAncillaryRepository.save(fcAncillary);

        return convertToResponse(saved);
    }

    @Override
    public FlightCabinAncillaryResponse getById(Long id) throws Exception {
        FlightCabinAncillary fcAncillary = flightCabinAncillaryRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found with given id!"));

        return convertToResponse(fcAncillary);
    }

    @Override
    public List<FlightCabinAncillaryResponse> getByFlightAndCabinClass(Long flightId, Long cabinClassId) {
        return flightCabinAncillaryRepository.findByFlightIdAndCabinClassId(
                flightId, cabinClassId).stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public List<FlightCabinAncillaryResponse> getAllByIds(List<Long> ids) {
        return flightCabinAncillaryRepository.findAllById(ids)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public FlightCabinAncillaryResponse getByFlightIdAndCabinClassIdAndType(
            Long flightId, Long cabinClassId, AncillaryType type) {
        FlightCabinAncillary fcAncillary = flightCabinAncillaryRepository
                .findByFlightIdAndCabinClassIdAndAncillary_Type(
                        flightId,
                        cabinClassId,
                        type
                );
        return convertToResponse(fcAncillary);
    }

    @Override
    public List<FlightCabinAncillaryResponse> getAllByFlightIdAndCabinClassIdAndType(Long flightId, Long cabinClassId, AncillaryType type) {
        return flightCabinAncillaryRepository
                .findAllByFlightIdAndCabinClassIdAndAncillary_Type(
                        flightId, cabinClassId, type)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public FlightCabinAncillaryResponse updateFlightCabinAncillary(
            Long id,FlightCabinAncillaryRequest request) throws Exception {
        FlightCabinAncillary existing = flightCabinAncillaryRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found with given id!"));
        Ancillary ancillary = ancillaryRepository.findById(request.getAncillaryId())
                .orElseThrow(() -> new Exception("Ancillary with given id not found!"));

        FlightCabinAncillaryMapper.updateEntity(request, existing, ancillary);
        FlightCabinAncillary updated = flightCabinAncillaryRepository.save(existing);

        return convertToResponse(updated);
    }

    @Override
    public void deleteFlightCabinAncillary(Long id) throws Exception {
        FlightCabinAncillary fcAncillary = flightCabinAncillaryRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found with given id!"));

        flightCabinAncillaryRepository.delete(fcAncillary);
    }

    @Override
    public Double calculateAncillaryPrice(List<Long> ancillaryIds) {
        List<FlightCabinAncillary> ancillaries = flightCabinAncillaryRepository
                .findAllById(ancillaryIds);
        double totalPrice = 0.0;
        for(FlightCabinAncillary ancillary : ancillaries) {
            totalPrice += ancillary.getPrice();
        }

        return totalPrice;
    }

    private FlightCabinAncillaryResponse convertToResponse(
            FlightCabinAncillary flightCabinAncillary) {
        List<InsuranceCoverage> coverages = insuranceCoverageRepository.findByAncillaryId(
                flightCabinAncillary.getId());
        List<InsuranceCoverageResponse> coverageResponses = coverages.stream()
                .map(InsuranceCoverageMapper::toResponse)
                .toList();

        return FlightCabinAncillaryMapper.toResponse(
                flightCabinAncillary, coverageResponses);
    }
}
