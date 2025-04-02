package com.chapssal_tteok.preview.domain.resume.service;

import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.domain.resume.repository.ResumeRepository;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.ResumeHandler;
import com.chapssal_tteok.preview.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResumeQueryServiceImpl implements ResumeQueryService {

    private final ResumeRepository resumeRepository;
    private final SecurityUtil securityUtil;

    @Override
    public Resume getResumeById(Long resumeId) {

        // 현재 로그인 상태 확인
        securityUtil.getCurrentUser();

        return resumeRepository.findWithQasById(resumeId)
                .orElseThrow(() -> new ResumeHandler(ErrorStatus.RESUME_NOT_FOUND));
    }
}
