package com.msp.embaddables;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class SeatBenefits {

    private boolean extraSeatSpace = false;

    private boolean preferredSeatChoice = false;

    private boolean advanceSeatSelection = false;

    private boolean guaranteedSeatsTogether = false;
}
