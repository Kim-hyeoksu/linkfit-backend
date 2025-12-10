package com.linkfit.linkfit_web.plan.application;

import com.linkfit.linkfit_web.common.exception.ResourceNotFoundException;
import com.linkfit.linkfit_web.plan.domain.Plan;
import com.linkfit.linkfit_web.plan.domain.PlanRepository;
import com.linkfit.linkfit_web.plan.dto.PlanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;

    public Page<PlanResponse> getStandalonePlans(Pageable pageable) {
        return planRepository.findByProgramIsNull(pageable).map(PlanResponse::from);
    }

    public Page<PlanResponse> getPlansByProgram(Long programId, Pageable pageable) {
        if (programId == null) {
            throw new IllegalArgumentException("programId is required.");
        }
        return planRepository.findByProgramId(programId, pageable).map(PlanResponse::from);
    }

    public PlanResponse getPlan(Long planId) {
        Plan plan =
                planRepository
                        .findById(planId)
                        .orElseThrow(() -> new ResourceNotFoundException("Plan not found."));
        return PlanResponse.from(plan);
    }
}
