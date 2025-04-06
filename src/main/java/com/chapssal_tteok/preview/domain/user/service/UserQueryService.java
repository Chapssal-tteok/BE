package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.user.dto.UserResponseDTO;

public interface UserQueryService {

    boolean isUsernameExist(String username);

    UserResponseDTO.UserInfoDTO getUserInfo();
}
