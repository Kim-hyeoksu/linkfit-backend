package com.linkfit.linkfit_web.plan.presentation;

import com.linkfit.linkfit_web.plan.application.PlanService;
import com.linkfit.linkfit_web.plan.dto.PlanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    // programId가 없는 독립 플랜 목록
    @GetMapping("/standalone")
    public ResponseEntity<Page<PlanResponse>> getStandalonePlans(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(planService.getStandalonePlans(pageable));
    }

    // 특정 프로그램에 종속된 플랜 목록
    @GetMapping
    public ResponseEntity<Page<PlanResponse>> getPlansByProgram(
            @RequestParam Long programId,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(planService.getPlansByProgram(programId, pageable));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponse> getPlan(@PathVariable Long planId) {
        return ResponseEntity.ok(planService.getPlan(planId));
    }
}
