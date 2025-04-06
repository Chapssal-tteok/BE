package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.user.entity.User;

public interface UserQueryService {

    boolean isUsernameExist(String username);

    User getCurrentUser();
}
