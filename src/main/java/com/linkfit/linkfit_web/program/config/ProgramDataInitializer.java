package com.linkfit.linkfit_web.program.config;

import com.linkfit.linkfit_web.program.domain.DifficultyLevel;
import com.linkfit.linkfit_web.program.domain.ExerciseProgram;
import com.linkfit.linkfit_web.program.domain.ExerciseProgramRepository;
import com.linkfit.linkfit_web.program.domain.ProgramStatus;
import com.linkfit.linkfit_web.program.domain.ProgramType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class ProgramDataInitializer implements CommandLineRunner {

    private final ExerciseProgramRepository exerciseProgramRepository;

    @Override
    public void run(String... args) {
        if (exerciseProgramRepository.count() > 0) {
            return;
        }

        List<ExerciseProgram> seeds =
                List.of(
                        ExerciseProgram.builder()
                                .categoryId(10L)
                                .createdUserId(1L)
                                .programName("Upper body strength")
                                .description("Bench- incline bench - lat pulldown - row, 4 sets each")
                                .level(DifficultyLevel.INTERMEDIATE)
                                .estimatedDurationMinutes(70)
                                .estimatedCalories(500)
                                .programType(ProgramType.POPULAR)
                                .status(ProgramStatus.PUBLISHED)
                                .likeCount(95)
                                .useCount(40)
                                .build(),
                        ExerciseProgram.builder()
                                .categoryId(20L)
                                .createdUserId(1L)
                                .programName("Lower body and core")
                                .description("Squat - Romanian deadlift - leg press - plank")
                                .level(DifficultyLevel.ADVANCED)
                                .estimatedDurationMinutes(80)
                                .estimatedCalories(650)
                                .programType(ProgramType.POPULAR)
                                .status(ProgramStatus.PUBLISHED)
                                .likeCount(88)
                                .useCount(35)
                                .build(),
                        ExerciseProgram.builder()
                                .categoryId(30L)
                                .createdUserId(2L)
                                .programName("3-day beginner split")
                                .description("Full body, 3 days per week, RPE 7 targets")
                                .level(DifficultyLevel.BEGINNER)
                                .estimatedDurationMinutes(60)
                                .estimatedCalories(400)
                                .programType(ProgramType.SHARED)
                                .status(ProgramStatus.PUBLISHED)
                                .likeCount(76)
                                .useCount(28)
                                .build(),
                        ExerciseProgram.builder()
                                .categoryId(40L)
                                .createdUserId(99L)
                                .programName("My private routine")
                                .description("Private program for personal use")
                                .level(DifficultyLevel.INTERMEDIATE)
                                .estimatedDurationMinutes(55)
                                .estimatedCalories(350)
                                .programType(ProgramType.PERSONAL)
                                .status(ProgramStatus.DRAFT)
                                .likeCount(10)
                                .useCount(5)
                                .build());

        exerciseProgramRepository.saveAll(seeds);
    }
}
