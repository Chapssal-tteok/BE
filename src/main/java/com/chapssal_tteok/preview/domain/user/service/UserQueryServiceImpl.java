package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.interview.repository.InterviewRepository;
import com.chapssal_tteok.preview.domain.resume.repository.ResumeRepository;
import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.domain.user.entity.User;
import com.chapssal_tteok.preview.domain.user.repository.UserRepository;
import com.chapssal_tteok.preview.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final InterviewRepository interviewRepository;
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

    @Override
    public List<Resume> getMyResumes() {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        return resumeRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<Interview> getMyInterviews() {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        return interviewRepository.findAllByUserId(user.getId());
    }
}
