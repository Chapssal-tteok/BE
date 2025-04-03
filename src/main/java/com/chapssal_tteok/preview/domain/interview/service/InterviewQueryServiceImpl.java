package com.chapssal_tteok.preview.domain.interview.service;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.interview.repository.InterviewRepository;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.InterviewHandler;
import com.chapssal_tteok.preview.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewQueryServiceImpl implements InterviewQueryService {

    private final InterviewRepository interviewRepository;
    private final SecurityUtil securityUtil;

    @Override
    public Interview getInterviewById(Long interviewId) {

        // 현재 로그인 상태 확인
        securityUtil.getCurrentUser();

        return interviewRepository.findWithQasById(interviewId)
                .orElseThrow(() -> new InterviewHandler(ErrorStatus.INTERVIEW_NOT_FOUND));
    }
}
