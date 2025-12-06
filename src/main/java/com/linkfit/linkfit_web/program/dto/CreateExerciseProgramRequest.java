package com.linkfit.linkfit_web.program.dto;

import com.linkfit.linkfit_web.program.domain.DifficultyLevel;
import com.linkfit.linkfit_web.program.domain.ProgramStatus;
import com.linkfit.linkfit_web.program.domain.ProgramType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExerciseProgramRequest {

    @NotNull
    private Long categoryId;

    @NotNull
    private Long createdUserId;

    @NotBlank
    @Size(max = 100)
    private String programName;

    @Size(max = 1000)
    private String description;

    @NotNull
    private DifficultyLevel level;

    @NotNull
    private Integer estimatedDurationMinutes;

    @NotNull
    private Integer estimatedCalories;

    @NotNull
    private ProgramType programType;

    @NotNull
    private ProgramStatus status;
}
