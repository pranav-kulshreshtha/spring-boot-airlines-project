package com.msp.models;

import com.msp.enums.SeatAvailabilityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
public class SeatInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long flightId;

    @ManyToOne
    private FlightInstanceCabin flightInstanceCabin;

    private Long flightInstanceId;

    @ManyToOne
    private Seat seat;

    private SeatAvailabilityStatus seatAvailabilityStatus
            = SeatAvailabilityStatus.AVAILABLE;

    private Boolean isBooked = false;

    private Boolean isAvailable = true;

    private Double fare;

    private Double premiumSupercharge;

    private Long flightScheduleId;

    @Version
    private Long version;

    @CreatedDate
    @Column(nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

}
