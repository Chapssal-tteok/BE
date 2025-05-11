package com.chapssal_tteok.preview.domain.interviewqa.service;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.interview.repository.InterviewRepository;
import com.chapssal_tteok.preview.domain.interviewqa.converter.InterviewQaConverter;
import com.chapssal_tteok.preview.domain.interviewqa.dto.InterviewQaRequestDTO;
import com.chapssal_tteok.preview.domain.interviewqa.entity.InterviewQa;
import com.chapssal_tteok.preview.domain.interviewqa.repository.InterviewQaRepository;
import com.chapssal_tteok.preview.domain.user.entity.Role;
import com.chapssal_tteok.preview.domain.user.entity.User;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.InterviewHandler;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.InterviewQaHandler;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.UserHandler;
import com.chapssal_tteok.preview.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterviewQaCommandServiceImpl implements InterviewQaCommandService {

    private final InterviewQaRepository interviewQaRepository;
    private final InterviewRepository interviewRepository;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public InterviewQa createInterviewQa(Long interviewId, InterviewQaRequestDTO.CreateInterviewQaDTO request) {

        // 현재 로그인 상태 확인
        securityUtil.getCurrentUser();

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewHandler(ErrorStatus.INTERVIEW_NOT_FOUND));

        // 현재 interview의 마지막 orderIndex를 조회
        Integer maxOrder = interviewQaRepository.findMaxOrderIndexByInterview(interview);
        int nextOrderIndex = maxOrder + 1;

        InterviewQa newInterviewQa = InterviewQaConverter.toInterviewQa(request, interview, nextOrderIndex);
        newInterviewQa.updateOrderIndex(nextOrderIndex);

        return interviewQaRepository.save(newInterviewQa);
    }

    @Override
    @Transactional
    public InterviewQa updateInterviewQa(Long interviewId, Long interviewQaId, InterviewQaRequestDTO.UpdateInterviewQaDTO request) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        InterviewQa interviewQa = interviewQaRepository.findById(interviewQaId)
                .orElseThrow(() -> new InterviewQaHandler(ErrorStatus.INTERVIEW_QA_NOT_FOUND));

        // URL의 interviewId와 DB의 interviewQa의 interview ID가 일치하는지 확인
        if (!interviewQa.getInterview().getId().equals(interviewId)) {
            throw new InterviewQaHandler(ErrorStatus.INTERVIEW_QA_NOT_MATCH);
        }

        // 자기 자신이거나 관리자 권한이 있는 경우만 허용
        if (!user.getId().equals(interviewQa.getInterview().getUser().getId()) &&
                !user.getRole().equals(Role.ADMIN)) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        if (request.getQuestion() != null) {
            interviewQa.updateQuestion(request.getQuestion());
        }
        if (request.getQuestionAudio() != null) {
            interviewQa.updateQuestionAudio(request.getQuestionAudio());
        }
        if (request.getAnswer() != null) {
            interviewQa.updateAnswer(request.getAnswer());
        }
        if (request.getAnswerAudio() != null) {
            interviewQa.updateAnswerAudio(request.getAnswerAudio());
        }
        if (request.getAnalysis() != null) {
            interviewQa.updateAnalysis(request.getAnalysis());
        }

        return interviewQaRepository.save(interviewQa);
    }

    @Override
    @Transactional
    public void deleteInterviewQa(Long interviewId, Long interviewQaId) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        InterviewQa interviewQa = interviewQaRepository.findById(interviewQaId)
                .orElseThrow(() -> new InterviewQaHandler(ErrorStatus.INTERVIEW_QA_NOT_FOUND));

        // URL의 interviewId와 DB의 interviewQa의 interview ID가 일치하는지 확인
        if (!interviewQa.getInterview().getId().equals(interviewId)) {
            throw new InterviewQaHandler(ErrorStatus.INTERVIEW_QA_NOT_MATCH);
        }

        // 자기 자신이거나 관리자 권한이 있는 경우만 허용
        if (!user.getId().equals(interviewQa.getInterview().getUser().getId()) &&
                !user.getRole().equals(Role.ADMIN)) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        interviewQaRepository.delete(interviewQa);
    }
}
