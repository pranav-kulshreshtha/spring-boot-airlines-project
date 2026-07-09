package com.msp.models;

import com.msp.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Duration;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class FlightInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long airlineId;

    @ManyToOne
    private Flight flight;

    @Column(nullable = false)
    private Long departureAirportId;

    @Column(nullable = false)
    private Long arrivalAirportId;

    @Column(nullable = false)
    private Long scheduleId;

    @Column(nullable = false)
    private LocalDateTime departureDateTime;

    @Column(nullable = false)
    private LocalDateTime arrivalDateTime;

    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Integer availableSeats;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    private Integer minAdvanceBookingDays;

    private Integer maxAdvanceBookingDays;

    private Boolean isActive = true;

    @Transient
    public String getFormattedDuration() {
        if(departureDateTime == null || arrivalDateTime == null) {
            return null;
        }

        Duration duration = Duration.between(
                departureDateTime, arrivalDateTime
        );
        Long hours = duration.toHours();
        Long minutes = duration.toMinutes();
        StringBuilder sb = new StringBuilder();
        if(hours > 0)sb.append(hours).append("h ");
        if(minutes > 0)sb.append(minutes).append("min");
        return sb.toString().trim();
    }
}
