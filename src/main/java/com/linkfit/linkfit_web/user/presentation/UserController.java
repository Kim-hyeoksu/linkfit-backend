package com.linkfit.linkfit_web.user.presentation;

import com.linkfit.linkfit_web.user.application.UserService;
import com.linkfit.linkfit_web.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }
}
