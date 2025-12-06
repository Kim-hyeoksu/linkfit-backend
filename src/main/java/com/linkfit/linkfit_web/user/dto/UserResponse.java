package com.linkfit.linkfit_web.user.dto;

import com.linkfit.linkfit_web.user.domain.ExerciseLevel;
import com.linkfit.linkfit_web.user.domain.Gender;
import com.linkfit.linkfit_web.user.domain.User;
import com.linkfit.linkfit_web.user.domain.UserStatus;
import com.linkfit.linkfit_web.user.domain.UserType;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private final Long id;
    private final String email;
    private final String name;
    private final String phone;
    private final String countryCode;
    private final boolean phoneVerified;
    private final LocalDate birthDate;
    private final Gender gender;
    private final BigDecimal height;
    private final BigDecimal weight;
    private final ExerciseLevel exerciseLevel;
    private final String profileImage;
    private final UserType userType;
    private final UserStatus status;
    private final Instant createdAt;
    private final Instant modifiedAt;
    private final Instant lastLoginAt;
    private final String lastLoginIp;
    private final Integer loginAttemptCount;
    private final Instant accountLockedUntil;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .countryCode(user.getCountryCode())
                .phoneVerified(user.isPhoneVerified())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .height(user.getHeight())
                .weight(user.getWeight())
                .exerciseLevel(user.getExerciseLevel())
                .profileImage(user.getProfileImage())
                .userType(user.getUserType())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .lastLoginAt(user.getLastLoginAt())
                .lastLoginIp(user.getLastLoginIp())
                .loginAttemptCount(user.getLoginAttemptCount())
                .accountLockedUntil(user.getAccountLockedUntil())
                .build();
    }
}
