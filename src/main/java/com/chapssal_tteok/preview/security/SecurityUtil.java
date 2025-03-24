package com.chapssal_tteok.preview.security;

import com.chapssal_tteok.preview.domain.user.entity.User;
import com.chapssal_tteok.preview.domain.user.repository.UserRepository;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SecurityUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new UserHandler(ErrorStatus._UNAUTHORIZED);
        }

        return userRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new UserHandler(ErrorStatus.USER_NOT_FOUND)
        );
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal());
    }
}
