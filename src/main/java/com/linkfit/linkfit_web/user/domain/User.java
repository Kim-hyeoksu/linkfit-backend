package com.linkfit.linkfit_web.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
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
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(length = 100)
    private String name;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(nullable = false, length = 10)
    private String countryCode;

    @Column(nullable = false)
    @Builder.Default
    private boolean phoneVerified = false;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Column(nullable = false, precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal height = BigDecimal.ZERO;

    @Column(nullable = false, precision = 6, scale = 2)
    @Builder.Default
    private BigDecimal weight = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    @Builder.Default
    private ExerciseLevel exerciseLevel = ExerciseLevel.LOW;

    @Column(length = 255)
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private UserType userType = UserType.TRAINEE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant modifiedAt;

    private Instant lastLoginAt;

    @Column(length = 64)
    private String lastLoginIp;

    @Column(nullable = false)
    @Builder.Default
    private Integer loginAttemptCount = 0;

    private Instant accountLockedUntil;
}
