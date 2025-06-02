package com.chapssal_tteok.preview.domain.interview.service;

import com.chapssal_tteok.preview.domain.interview.converter.InterviewConverter;
import com.chapssal_tteok.preview.domain.interview.dto.InterviewRequestDTO;
import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.interview.repository.InterviewRepository;
import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.domain.resume.repository.ResumeRepository;
import com.chapssal_tteok.preview.domain.user.entity.Role;
import com.chapssal_tteok.preview.domain.user.entity.User;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.InterviewHandler;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.UserHandler;
import com.chapssal_tteok.preview.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterviewCommandServiceImpl implements InterviewCommandService {

    private final InterviewRepository interviewRepository;
    private final ResumeRepository resumeRepository;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public Interview createInterview(InterviewRequestDTO.CreateInterviewDTO request) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        Resume resume = resumeRepository.findById(request.getResumeId())
                .orElseThrow(() -> new InterviewHandler(ErrorStatus.RESUME_NOT_FOUND));

        Interview newInterview = InterviewConverter.toInterview(request, user, resume);

        return interviewRepository.save(newInterview);
    }

    @Override
    @Transactional
    public Interview updateInterview(Long interviewId, InterviewRequestDTO.UpdateInterviewDTO request) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewHandler(ErrorStatus.INTERVIEW_NOT_FOUND));

        // 자기 자신이거나 관리자 권한이 있는 경우만 허용
        if (!user.getId().equals(interview.getUser().getId()) &&
                !user.getRole().equals(Role.ADMIN)) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        if (request.getTitle() != null) {
            interview.updateTitle(request.getTitle());
        }

        return interviewRepository.save(interview);
    }

    @Override
    @Transactional
    public void deleteInterview(Long interviewId) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewHandler(ErrorStatus.INTERVIEW_NOT_FOUND));

        // 자기 자신이거나 관리자 권한이 있는 경우만 허용
        if (!user.getId().equals(interview.getUser().getId()) &&
                !user.getRole().equals(Role.ADMIN)) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        interviewRepository.delete(interview);
    }
}
