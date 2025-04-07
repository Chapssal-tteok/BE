package com.chapssal_tteok.preview.domain.resumeqa.service;

import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.domain.resume.repository.ResumeRepository;
import com.chapssal_tteok.preview.domain.resumeqa.converter.ResumeQaConverter;
import com.chapssal_tteok.preview.domain.resumeqa.dto.ResumeQaRequestDTO;
import com.chapssal_tteok.preview.domain.resumeqa.entity.ResumeQa;
import com.chapssal_tteok.preview.domain.resumeqa.repository.ResumeQaRepository;
import com.chapssal_tteok.preview.domain.user.entity.Role;
import com.chapssal_tteok.preview.domain.user.entity.User;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.ResumeHandler;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.ResumeQaHandler;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.UserHandler;
import com.chapssal_tteok.preview.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeQaCommandServiceImpl implements ResumeQaCommandService {

    private final ResumeQaRepository resumeQaRepository;
    private final ResumeRepository resumeRepository;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public ResumeQa createResumeQa(Long resumeId, ResumeQaRequestDTO.CreateResumeQaDTO request) {

        // 현재 로그인 상태 확인
        securityUtil.getCurrentUser();

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResumeHandler(ErrorStatus.RESUME_NOT_FOUND));

        ResumeQa newResumeQa = ResumeQaConverter.toResumeQa(request, resume);

        return resumeQaRepository.save(newResumeQa);
    }

    @Override
    @Transactional
    public ResumeQa updateResumeQa(Long resumeId, Long resumeQaId, ResumeQaRequestDTO.UpdateResumeQaDTO request) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        ResumeQa resumeQa = resumeQaRepository.findById(resumeQaId)
                .orElseThrow(() -> new ResumeQaHandler(ErrorStatus.RESUME_QA_NOT_FOUND));

        // URL의 resumeId와 DB의 resumeQa의 resume ID가 일치하는지 확인
        if (!resumeQa.getResume().getId().equals(resumeId)) {
            throw new ResumeQaHandler(ErrorStatus.RESUME_QA_NOT_MATCH);
        }

        // 자기 자신이거나 관리자 권한이 있는 경우만 허용
        if (!user.getId().equals(resumeQa.getResume().getUser().getId()) &&
                !user.getRole().equals(Role.ADMIN)) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        if (request.getQuestion() != null) {
            resumeQa.updateQuestion(request.getQuestion());
        }
        if (request.getAnswer() != null) {
            resumeQa.updateAnswer(request.getAnswer());
        }
        if (request.getAnalysis() != null) {
            resumeQa.updateAnalysis(request.getAnalysis());
        }

        return resumeQaRepository.save(resumeQa);
    }

    @Override
    @Transactional
    public void deleteResumeQa(Long resumeId, Long resumeQaId) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        ResumeQa resumeQa = resumeQaRepository.findById(resumeQaId)
                .orElseThrow(() -> new ResumeQaHandler(ErrorStatus.RESUME_QA_NOT_FOUND));

        // URL의 resumeId와 DB의 resumeQa의 resume ID가 일치하는지 확인
        if (!resumeQa.getResume().getId().equals(resumeId)) {
            throw new ResumeQaHandler(ErrorStatus.RESUME_QA_NOT_MATCH);
        }

        // 자기 자신이거나 관리자 권한이 있는 경우만 허용
        if (!user.getId().equals(resumeQa.getResume().getUser().getId()) &&
                !user.getRole().equals(Role.ADMIN)) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        resumeQaRepository.delete(resumeQa);
    }
}
