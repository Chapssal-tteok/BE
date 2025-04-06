package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.user.entity.User;
import com.chapssal_tteok.preview.domain.user.repository.UserRepository;
import com.chapssal_tteok.preview.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Override
    public boolean isUsernameExist(String username) {

        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public User getCurrentUser() {

        // 현재 로그인된 사용자 정보 가져오기
        return securityUtil.getCurrentUser();
    }
}
