package com.linkfit.linkfit_web.user.application;

import com.linkfit.linkfit_web.common.exception.ResourceNotFoundException;
import com.linkfit.linkfit_web.user.domain.User;
import com.linkfit.linkfit_web.user.domain.UserRepository;
import com.linkfit.linkfit_web.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUser(Long userId) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        return UserResponse.from(user);
    }
}
