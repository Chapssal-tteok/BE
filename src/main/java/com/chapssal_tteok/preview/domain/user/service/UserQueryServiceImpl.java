package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.user.converter.UserConverter;
import com.chapssal_tteok.preview.domain.user.dto.UserResponseDTO;
import com.chapssal_tteok.preview.domain.user.entity.User;
import com.chapssal_tteok.preview.domain.user.repository.UserRepository;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.UserHandler;
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
    public UserResponseDTO.UserInfoDTO getUserInfo() {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        return UserConverter.toUserInfoDTO(user);
    }
}
