package com.msp.services.impl;

import com.msp.enums.CabinClassType;
import com.msp.mappers.CabinClassMapper;
import com.msp.models.CabinClass;
import com.msp.payloads.requests.CabinClassRequest;
import com.msp.payloads.responses.CabinClassResponse;
import com.msp.repositories.CabinClassRepository;
import com.msp.services.CabinClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CabinClassServiceImpl implements CabinClassService {

    private final CabinClassRepository cabinClassRepository;

    @Override
    public CabinClassResponse createCabinClass(CabinClassRequest request) throws Exception {

        if(cabinClassRepository.existsByCodeAndAircraftId(
                request.getCode(), request.getAircraftId())) {
            throw new Exception("Cabin class with code already exists!");
        }

        CabinClass cabinClass = CabinClassMapper.toEntity(request);
        CabinClass saved = cabinClassRepository.save(cabinClass);

        return CabinClassMapper.toResponse(saved);
    }

    @Override
    public CabinClassResponse getCabinClassById(Long id) throws Exception {
        CabinClass cabinClass = cabinClassRepository.findById(id)
                .orElseThrow(() -> new Exception("Cabin class with given" +
                        " id does not exist!"));

        return CabinClassMapper.toResponse(cabinClass);
    }

    @Override
    public List<CabinClassResponse> getCabinClassByAircraftId(Long aircraftId) {
        return cabinClassRepository.findByAircraftId(aircraftId)
                .stream().map(CabinClassMapper::toResponse)
                .toList();
    }

    @Override
    public CabinClassResponse getByAircraftIdAndName(Long aircraftId, CabinClassType name) {
        CabinClass cabinClass = cabinClassRepository.findByAircraftIdAndName(
                aircraftId, name);

        return CabinClassMapper.toResponse(cabinClass);
    }

    @Override
    public CabinClassResponse updateCabinClass(Long id, CabinClassRequest request) throws Exception {
        CabinClass existing = cabinClassRepository.findById(id)
                .orElseThrow(() -> new Exception("Cabin class with given" +
                        " id does not exist!"));

        if(cabinClassRepository.existsByCodeAndAircraftIdAndIdNot(
                request.getCode().toUpperCase(),
                request.getAircraftId(),
                existing.getId()
        )) {
            throw new Exception("Cabin class with given code already exists!");
        }

        CabinClassMapper.updateEntity(request, existing);
        CabinClass updated = cabinClassRepository.save(existing);

        return CabinClassMapper.toResponse(updated);
    }

    @Override
    public void deleteCabinClass(Long id) throws Exception {
        CabinClass cabinClass = cabinClassRepository.findById(id)
                .orElseThrow(() -> new Exception("Cabin class with given" +
                        " id does not exist!"));
        cabinClassRepository.delete(cabinClass);
    }
}
