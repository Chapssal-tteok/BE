package com.chapssal_tteok.preview.domain.resumeqa.service;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.interviewqa.entity.InterviewQa;
import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.domain.resume.repository.ResumeRepository;
import com.chapssal_tteok.preview.domain.resumeqa.entity.ResumeQa;
import com.chapssal_tteok.preview.domain.resumeqa.repository.ResumeQaRepository;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.InterviewQaHandler;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.ResumeQaHandler;
import com.chapssal_tteok.preview.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResumeQaQueryServiceImpl implements ResumeQaQueryService {

    private final ResumeQaRepository resumeQaRepository;
    private final ResumeRepository resumeRepository;
    private final SecurityUtil securityUtil;

    @Override
    public ResumeQa getResumeQaById(Long resumeQaId) {

        // 현재 로그인 상태 확인
        securityUtil.getCurrentUser();

        return resumeQaRepository.findById(resumeQaId)
                .orElseThrow(() -> new ResumeQaHandler(ErrorStatus.RESUME_QA_NOT_FOUND));
    }

    @Override
    public List<ResumeQa> getOrderedQasByResumeId(Long resumeId) {

        // 현재 로그인 상태 확인
        securityUtil.getCurrentUser();

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResumeQaHandler(ErrorStatus.RESUME_QA_NOT_FOUND));

        return resumeQaRepository.findAllByResumeOrderByOrderIndexAsc(resume);
    }
}
