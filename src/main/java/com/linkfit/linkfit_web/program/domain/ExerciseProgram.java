package com.linkfit.linkfit_web.program.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "exercise_programs")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ExerciseProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private Long createdUserId;

    @Column(nullable = false, length = 100)
    private String programName;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DifficultyLevel level;

    @Column(nullable = false)
    private Integer estimatedDurationMinutes;

    @Column(nullable = false)
    private Integer estimatedCalories;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProgramType programType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProgramStatus status;

    @Column(nullable = false)
    @Builder.Default
    private Integer likeCount = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer useCount = 0;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    public void update(
            Long categoryId,
            String programName,
            String description,
            DifficultyLevel level,
            Integer estimatedDurationMinutes,
            Integer estimatedCalories,
            ProgramType programType,
            ProgramStatus status) {
        this.categoryId = categoryId;
        this.programName = programName;
        this.description = description;
        this.level = level;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
        this.estimatedCalories = estimatedCalories;
        this.programType = programType;
        this.status = status;
    }

    public boolean isOwner(Long ownerId) {
        return this.createdUserId.equals(ownerId);
    }
}
