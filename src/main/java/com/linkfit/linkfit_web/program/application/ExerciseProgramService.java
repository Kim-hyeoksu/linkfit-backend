package com.linkfit.linkfit_web.program.application;

import com.linkfit.linkfit_web.common.exception.ForbiddenOperationException;
import com.linkfit.linkfit_web.common.exception.ResourceNotFoundException;
import com.linkfit.linkfit_web.program.domain.ExerciseProgram;
import com.linkfit.linkfit_web.program.domain.ExerciseProgramRepository;
import com.linkfit.linkfit_web.program.domain.ProgramStatus;
import com.linkfit.linkfit_web.program.domain.ProgramType;
import com.linkfit.linkfit_web.program.dto.CreateExerciseProgramRequest;
import com.linkfit.linkfit_web.program.dto.ExerciseProgramResponse;
import com.linkfit.linkfit_web.program.dto.UpdateExerciseProgramRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExerciseProgramService {

    private final ExerciseProgramRepository programRepository;

    @Transactional(readOnly = true)
    public Page<ExerciseProgramResponse> getMyPrograms(Long ownerId, Pageable pageable) {
        if (ownerId == null) {
            throw new IllegalArgumentException("ownerId is required.");
        }
        return programRepository.findByCreatedUserId(ownerId, pageable).map(ExerciseProgramResponse::from);
    }

    @Transactional(readOnly = true)
    public Page<ExerciseProgramResponse> getPopularPrograms(Pageable pageable) {
        return programRepository
                .findByProgramTypeOrderByLikeCountDesc(ProgramType.POPULAR, pageable)
                .map(ExerciseProgramResponse::from);
    }

    @Transactional(readOnly = true)
    public ExerciseProgramResponse getProgram(Long programId) {
        return ExerciseProgramResponse.from(getProgramOrThrow(programId));
    }

    public ExerciseProgramResponse createProgram(CreateExerciseProgramRequest request) {
        ExerciseProgram program =
                ExerciseProgram.builder()
                        .categoryId(request.getCategoryId())
                        .createdUserId(request.getCreatedUserId())
                        .programName(request.getProgramName())
                        .description(request.getDescription())
                        .level(request.getLevel())
                        .estimatedDurationMinutes(request.getEstimatedDurationMinutes())
                        .estimatedCalories(request.getEstimatedCalories())
                        .programType(request.getProgramType())
                        .status(request.getStatus() != null ? request.getStatus() : ProgramStatus.DRAFT)
                        .likeCount(0)
                        .useCount(0)
                        .build();
        return ExerciseProgramResponse.from(programRepository.save(program));
    }

    public ExerciseProgramResponse updateProgram(Long programId, UpdateExerciseProgramRequest request) {
        ExerciseProgram program = getProgramOrThrow(programId);
        validateOwnership(program, request.getCreatedUserId());
        program.update(
                request.getCategoryId(),
                request.getProgramName(),
                request.getDescription(),
                request.getLevel(),
                request.getEstimatedDurationMinutes(),
                request.getEstimatedCalories(),
                request.getProgramType(),
                request.getStatus());
        return ExerciseProgramResponse.from(program);
    }

    public void deleteProgram(Long programId, Long ownerId) {
        ExerciseProgram program = getProgramOrThrow(programId);
        validateOwnership(program, ownerId);
        programRepository.delete(program);
    }

    private ExerciseProgram getProgramOrThrow(Long programId) {
        return programRepository
                .findById(programId)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise program not found."));
    }

    private void validateOwnership(ExerciseProgram program, Long ownerId) {
        if (!program.isOwner(ownerId)) {
            throw new ForbiddenOperationException("Only the program owner can modify or delete it.");
        }
    }
}
