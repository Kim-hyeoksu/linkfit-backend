package com.linkfit.linkfit_web.plan.dto;

import com.linkfit.linkfit_web.plan.domain.Plan;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanResponse {

    private final Long id;
    private final Long userId;
    private final Long programId;
    private final Integer dayOrder;
    private final String title;
    private final Instant createdAt;

    public static PlanResponse from(Plan plan) {
        return PlanResponse.builder()
                .id(plan.getId())
                .userId(plan.getUser() != null ? plan.getUser().getId() : null)
                .programId(plan.getProgram() != null ? plan.getProgram().getId() : null)
                .dayOrder(plan.getDayOrder())
                .title(plan.getTitle())
                .createdAt(plan.getCreatedAt())
                .build();
    }
}
