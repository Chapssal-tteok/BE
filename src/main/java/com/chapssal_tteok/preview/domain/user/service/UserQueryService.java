package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.domain.user.entity.User;

import java.util.List;

public interface UserQueryService {

    boolean isUsernameExist(String username);

    User getCurrentUser();

    List<Resume> getMyResumes();

    List<Interview> getMyInterviews();
}
