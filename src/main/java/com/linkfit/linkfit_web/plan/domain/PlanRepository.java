package com.linkfit.linkfit_web.plan.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Page<Plan> findByProgramIsNull(Pageable pageable);

    Page<Plan> findByProgramId(Long programId, Pageable pageable);
}
