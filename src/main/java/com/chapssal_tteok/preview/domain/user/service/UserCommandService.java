package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.user.dto.RegisterResponseDTO;
import com.chapssal_tteok.preview.domain.user.dto.UserRequestDTO;
import com.chapssal_tteok.preview.domain.user.entity.User;

public interface UserCommandService {

    RegisterResponseDTO registerUser (UserRequestDTO.CreateUserDTO userRequestDto);

    void deleteUser();

    boolean isUsernameUnique(String username);

    User updateUserInfo(UserRequestDTO.UpdateUserDTO updateUserDTO);
}
