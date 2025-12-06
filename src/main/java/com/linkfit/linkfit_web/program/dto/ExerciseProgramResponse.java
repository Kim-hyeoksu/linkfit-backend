package com.linkfit.linkfit_web.program.dto;

import com.linkfit.linkfit_web.program.domain.DifficultyLevel;
import com.linkfit.linkfit_web.program.domain.ExerciseProgram;
import com.linkfit.linkfit_web.program.domain.ProgramStatus;
import com.linkfit.linkfit_web.program.domain.ProgramType;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExerciseProgramResponse {

    private final Long id;
    private final Long categoryId;
    private final Long createdUserId;
    private final String programName;
    private final String description;
    private final DifficultyLevel level;
    private final Integer estimatedDurationMinutes;
    private final Integer estimatedCalories;
    private final ProgramType programType;
    private final ProgramStatus status;
    private final Integer likeCount;
    private final Integer useCount;
    private final Instant createdAt;
    private final Instant updatedAt;

    public static ExerciseProgramResponse from(ExerciseProgram program) {
        return ExerciseProgramResponse.builder()
                .id(program.getId())
                .categoryId(program.getCategoryId())
                .createdUserId(program.getCreatedUserId())
                .programName(program.getProgramName())
                .description(program.getDescription())
                .level(program.getLevel())
                .estimatedDurationMinutes(program.getEstimatedDurationMinutes())
                .estimatedCalories(program.getEstimatedCalories())
                .programType(program.getProgramType())
                .status(program.getStatus())
                .likeCount(program.getLikeCount())
                .useCount(program.getUseCount())
                .createdAt(program.getCreatedAt())
                .updatedAt(program.getUpdatedAt())
                .build();
    }
}
