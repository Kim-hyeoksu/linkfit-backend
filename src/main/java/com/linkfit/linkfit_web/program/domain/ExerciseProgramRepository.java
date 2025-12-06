package com.linkfit.linkfit_web.program.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseProgramRepository extends JpaRepository<ExerciseProgram, Long> {

    Page<ExerciseProgram> findByCreatedUserId(Long ownerId, Pageable pageable);

    Page<ExerciseProgram> findByProgramTypeOrderByLikeCountDesc(
            ProgramType programType, Pageable pageable);
}
