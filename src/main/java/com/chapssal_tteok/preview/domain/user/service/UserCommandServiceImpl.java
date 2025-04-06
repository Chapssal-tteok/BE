package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.user.dto.RegisterResponseDTO;
import com.chapssal_tteok.preview.domain.user.dto.UserRequestDTO;
import com.chapssal_tteok.preview.domain.user.entity.User;
import com.chapssal_tteok.preview.domain.user.repository.UserRepository;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.UserHandler;
import com.chapssal_tteok.preview.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtil securityUtil;

    @Override
    public RegisterResponseDTO registerUser(UserRequestDTO.CreateUserDTO userRequestDto) {

        // Email Already Exists
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new UserHandler(ErrorStatus.EMAIL_ALREADY_EXISTS);
        }

        // Username Already Exists
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new UserHandler(ErrorStatus.USERNAME_ALREADY_EXISTS);
        }

        // Password Encoding
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());

        // UserDto to User
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .password(encodedPassword)
                .role(com.chapssal_tteok.preview.domain.user.entity.Role.USER) // 기본적으로 USER 부여
                .build();

        userRepository.save(user);

        return RegisterResponseDTO.builder()
                .username(user.getUsername())
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .build();
    }

    @Override
    public void deleteUser() {

        // 현재 로그인된 사용자 정보 가져오기
        User currentUser = securityUtil.getCurrentUser(); // 로그인한 사용자 (username 또는 userId 기반)

        if (currentUser == null) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        // 🛠 해결: JPA 영속 상태로 변환
        User persistentUser = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        userRepository.delete(persistentUser);
    }

    @Override
    public User updateUserInfo(UserRequestDTO.UpdateUserDTO updateUserDTO) {

        User currentUser = securityUtil.getCurrentUser();
        Boolean isChanged = false;

        if (updateUserDTO.getName() != null) {
            currentUser.setName(updateUserDTO.getName());
            isChanged = true;
        }

        if (!passwordEncoder.matches(updateUserDTO.getCurrentPassword(), currentUser.getPassword())) {
            throw new UserHandler(ErrorStatus.INVALID_PASSWORD);
        } else {
            if (updateUserDTO.getPassword() != null) {
                currentUser.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
                isChanged = true;
            }
        }

        if (!isChanged) {
            throw new UserHandler(ErrorStatus.NO_CHANGE_DETECTED);
        }

        return currentUser;
    }
}
