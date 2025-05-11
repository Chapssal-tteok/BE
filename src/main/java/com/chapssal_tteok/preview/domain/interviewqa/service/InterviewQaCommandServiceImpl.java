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
import com.chapssal_tteok.preview.global.client.AiClient;
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
    private final AiClient aiClient;

    @Override
    @Transactional
    public InterviewQa createInterviewQa(Long interviewId, InterviewQaRequestDTO.CreateInterviewQaDTO request) {

        // 현재 로그인 상태 확인
        securityUtil.getCurrentUser();

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewHandler(ErrorStatus.INTERVIEW_NOT_FOUND));

        int nextOrder = interviewQaRepository.findMaxOrderIndexByInterview(interview) + 1;

        InterviewQa newInterviewQa = InterviewQaConverter.toInterviewQa(request, interview, nextOrder);

        return interviewQaRepository.save(newInterviewQa);
    }

    @Override
    @Transactional
    public InterviewQa generateInterviewQuestion(Long interviewId, InterviewQaRequestDTO.GenerateQuestionDTO request) {

        // 현재 로그인 상태 확인
        securityUtil.getCurrentUser();

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewHandler(ErrorStatus.INTERVIEW_NOT_FOUND));

        int nextOrder = interviewQaRepository.findMaxOrderIndexByInterview(interview) + 1;

        // AI 서버 호출
        String question = aiClient.generateQuestion(request);

        InterviewQa interviewQa = InterviewQa.builder()
                .interview(interview)
                .orderIndex(nextOrder)
                .question(question)
                .build();

        return interviewQaRepository.save(interviewQa);
    }

    @Override
    @Transactional
    public InterviewQa generateFollowUpQuestion(Long interviewId, InterviewQaRequestDTO.GenerateFollowUpDTO request) {

        // 현재 로그인 상태 확인
        securityUtil.getCurrentUser();

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewHandler(ErrorStatus.INTERVIEW_NOT_FOUND));

        int nextOrder = interviewQaRepository.findMaxOrderIndexByInterview(interview) + 1;

        // AI 서버 호출
        String question = aiClient.generateFollowUp(request);

        InterviewQa interviewQa = InterviewQa.builder()
                .interview(interview)
                .orderIndex(nextOrder)
                .question(question)
                .build();

        return interviewQaRepository.save(interviewQa);
    }

    @Override
    @Transactional
    public InterviewQa analyzeAnswer(Long interviewId, Long interviewQaId, InterviewQaRequestDTO.AnalyzeAnswerDTO request) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        InterviewQa interviewQa = validateQaOwnership(interviewId, interviewQaId, user);

        String analysis = aiClient.analyzeAnswer(request.getQuestion(), request.getAnswer(), request.getResume());
        interviewQa.updateAnalysis(analysis);

        return interviewQaRepository.save(interviewQa);
    }

    @Override
    @Transactional
    public InterviewQa updateQuestion(Long interviewId, Long interviewQaId, InterviewQaRequestDTO.UpdateQuestionDTO request) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        InterviewQa interviewQa = validateQaOwnership(interviewId, interviewQaId, user);

        interviewQa.updateQuestion(request.getQuestion());
        interviewQa.updateQuestionAudio(request.getQuestionAudio());
        interviewQa.updateAnswer(null);      // 답변 제거
        interviewQa.updateAnswerAudio(null);
        interviewQa.updateAnalysis(null);    // 분석 제거

        return interviewQaRepository.save(interviewQa);
    }

    @Override
    @Transactional
    public InterviewQa updateAnswer(Long interviewId, Long interviewQaId, InterviewQaRequestDTO.UpdateAnswerDTO request) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        InterviewQa interviewQa = validateQaOwnership(interviewId, interviewQaId, user);

        interviewQa.updateAnswer(request.getAnswer());
        interviewQa.updateAnswerAudio(request.getAnswerAudio());
        interviewQa.updateAnalysis(null);    // 분석 제거

        return interviewQaRepository.save(interviewQa);
    }

    @Override
    @Transactional
    public InterviewQa updateAnalysis(Long interviewId, Long interviewQaId, InterviewQaRequestDTO.UpdateAnalysisDTO request) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        InterviewQa interviewQa = validateQaOwnership(interviewId, interviewQaId, user);

        interviewQa.updateAnalysis(request.getAnalysis());

        return interviewQaRepository.save(interviewQa);
    }

    @Override
    @Transactional
    public void deleteInterviewQa(Long interviewId, Long interviewQaId) {

        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        InterviewQa interviewQa = validateQaOwnership(interviewId, interviewQaId, user);

        interviewQaRepository.delete(interviewQa);
    }

    private InterviewQa validateQaOwnership(Long interviewId, Long qaId, User user) {
        // interviewQa 존재 여부 확인
        InterviewQa qa = interviewQaRepository.findById(qaId)
                .orElseThrow(() -> new InterviewQaHandler(ErrorStatus.INTERVIEW_QA_NOT_FOUND));

        // URL의 interviewId와 DB의 interviewQa의 interview ID가 일치하는지 확인
        if (!qa.getInterview().getId().equals(interviewId)) {
            throw new InterviewQaHandler(ErrorStatus.INTERVIEW_QA_NOT_MATCH);
        }

        // 자기 자신이거나 관리자 권한이 있는 경우만 허용
        if (!user.getId().equals(qa.getInterview().getUser().getId()) && !user.getRole().equals(Role.ADMIN)) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        return qa;
    }
}
