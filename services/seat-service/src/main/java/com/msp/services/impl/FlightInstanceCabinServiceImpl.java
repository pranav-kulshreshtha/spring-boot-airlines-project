package com.msp.services.impl;

import com.msp.enums.SeatAvailabilityStatus;
import com.msp.enums.SeatType;
import com.msp.mappers.FlightInstanceCabinMapper;
import com.msp.models.CabinClass;
import com.msp.models.FlightInstanceCabin;
import com.msp.models.SeatInstance;
import com.msp.models.SeatMap;
import com.msp.payloads.requests.FlightInstanceCabinRequest;
import com.msp.payloads.responses.FlightInstanceCabinResponse;
import com.msp.repositories.CabinClassRepository;
import com.msp.repositories.FlightInstanceCabinRepository;
import com.msp.repositories.SeatInstanceRepository;
import com.msp.repositories.SeatMapRepository;
import com.msp.services.FlightInstanceCabinService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightInstanceCabinServiceImpl implements FlightInstanceCabinService {

    private final CabinClassRepository cabinClassRepository;
    private final SeatMapRepository seatMapRepository;
    private final FlightInstanceCabinRepository flightInstanceCabinRepository;
    private final SeatInstanceRepository seatInstanceRepository;

    @Override
    public FlightInstanceCabinResponse createFlightInstanceCabin(FlightInstanceCabinRequest request) throws Exception {
        CabinClass cabinClass = cabinClassRepository.findById(
                request.getCabinClassId())
                .orElseThrow(() -> new Exception("Cabin class not found!"));
        SeatMap seatMap = seatMapRepository.findByCabinClassId(cabinClass.getId());

        if(seatMap == null) {
            throw new Exception("Seat map not found!");
        }
        if(seatMap.getSeats() == null || seatMap.getSeats().isEmpty()) {
            throw new Exception("No seats found in the seat map!");
        }

        int totalSeats = seatMap.getSeats().size();
        FlightInstanceCabin flightInstanceCabin = FlightInstanceCabin.builder()
                .flightInstanceId(request.getFlightInstanceId())
                .cabinClass(cabinClass)
                .totalSeats(totalSeats)
                .bookedSeats(0)
                .build();
        FlightInstanceCabin savedCabin = flightInstanceCabinRepository.save(
                flightInstanceCabin);

        // generate seat instances
        List<SeatInstance> seatInstances = seatMap.getSeats().stream()
                .map(seat -> {
                    Double premiumSupercharge = getPremiumSupercharge(
                            seat.getSeatType(),
                            1000.0,
                            500.0
                    );

                    SeatInstance seatInstance = SeatInstance.builder()
                            .flightId(request.getFlightId())
                            .seatAvailabilityStatus(SeatAvailabilityStatus.AVAILABLE)
                            .flightInstanceId(request.getFlightInstanceId())
                            .flightInstanceCabin(savedCabin)
                            .seat(seat)
                            .isAvailable(true)
                            .isBooked(false)
                            .premiumSupercharge(premiumSupercharge)
                            .build();
                    return seatInstance;
                }).toList();

        seatInstanceRepository.saveAll(seatInstances);
        savedCabin.setSeats(seatInstances);

        return FlightInstanceCabinMapper.toResponse(savedCabin);
    }

    @Override
    public FlightInstanceCabinResponse getFlightInstanceCabinById(Long id) throws Exception {
        FlightInstanceCabin flightInstanceCabin = flightInstanceCabinRepository.findById(id)
                .orElseThrow(() -> new
                        Exception("Flight instance cabin not found with given id!"));
        return FlightInstanceCabinMapper.toResponse(flightInstanceCabin);
    }

    @Override
    public Page<FlightInstanceCabinResponse> getByFlightInstanceId(Long id, Pageable pageable) {
        return flightInstanceCabinRepository.findByFlightInstanceId(id, pageable)
                .map(FlightInstanceCabinMapper::toResponse);
    }

    @Override
    public FlightInstanceCabinResponse getByFlightInstanceIdAndCabinClassId(Long flightInstanceId, Long cabinClassId) {
        FlightInstanceCabin flightInstanceCabin = flightInstanceCabinRepository
                .findByFlightInstanceIdAndCabinClassId(flightInstanceId, cabinClassId);

        return FlightInstanceCabinMapper.toResponse(flightInstanceCabin);
    }

    @Override
    public FlightInstanceCabinResponse updateFlightInstanceCabin(Long id, FlightInstanceCabinRequest request)
            throws Exception {
        FlightInstanceCabin existing = flightInstanceCabinRepository.findById(id)
                .orElseThrow(() -> new
                        Exception("Flight instance cabin not found with given id!"));

        if(request.getCabinClassId()!=null) {
            CabinClass cabinClass = cabinClassRepository.findById(request.getCabinClassId())
                    .orElseThrow(() -> new
                            EntityNotFoundException("Cabin class not found with given id!"));
            existing.setCabinClass(cabinClass);
        }

        FlightInstanceCabin updated = flightInstanceCabinRepository.save(existing);

        return FlightInstanceCabinMapper.toResponse(updated);
    }

    @Override
    public void deleteFlightInstanceCabin(Long id) throws Exception {
        FlightInstanceCabin flightInstanceCabin = flightInstanceCabinRepository.findById(id)
                .orElseThrow(() -> new
                        Exception("Flight instance cabin not found with given id!"));
        flightInstanceCabinRepository.delete(flightInstanceCabin);
    }

    private Double getPremiumSupercharge(
            SeatType seatType,
            Double windowSupercharge,
            Double aisleSupercharge
    ) {
        if(seatType==null)return 0.0;

        return switch(seatType) {
            case AISLE -> aisleSupercharge;
            case WINDOW -> windowSupercharge;
            default -> 0.0;
        };
    }
}
