package com.chapssal_tteok.preview.domain.interviewqa.service;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.interview.repository.InterviewRepository;
import com.chapssal_tteok.preview.domain.interviewqa.entity.InterviewQa;
import com.chapssal_tteok.preview.domain.interviewqa.repository.InterviewQaRepository;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.InterviewQaHandler;
import com.chapssal_tteok.preview.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewQaQueryServiceImpl implements InterviewQaQueryService {

    private final InterviewQaRepository interviewQaRepository;
    private final InterviewRepository interviewRepository;
    private final SecurityUtil securityUtil;

    @Override
    public InterviewQa getInterviewQaById(Long interviewQaId) {

        // 현재 로그인 상태 확인
        securityUtil.getCurrentUser();

        return interviewQaRepository.findById(interviewQaId)
                .orElseThrow(() -> new InterviewQaHandler(ErrorStatus.INTERVIEW_QA_NOT_FOUND));
    }

    @Override
    public List<InterviewQa> getOrderedQasByInterviewId(Long interviewId) {

        // 현재 로그인 상태 확인
        securityUtil.getCurrentUser();

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewQaHandler(ErrorStatus.INTERVIEW_QA_NOT_FOUND));

        return interviewQaRepository.findAllByInterviewOrderByOrderIndexAsc(interview);
    }
}
