package com.msp.embaddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class FlexibilityBenefits {

    private Boolean freeDateChange = false;

    @Column(name = "partial_refund", nullable = false)
    @Builder.Default
    private Boolean partialRefund = false;

    @Column(name = "full_refund", nullable = false)
    @Builder.Default
    private Boolean fullRefund = false;
}
