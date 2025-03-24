package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.user.dto.UserResponseDTO;

public interface UserQueryService {

    UserResponseDTO.UserInfoDTO getUserInfo(String username);
}
