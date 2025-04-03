package com.chapssal_tteok.preview.domain.interviewqa.service;

import com.chapssal_tteok.preview.domain.interviewqa.dto.InterviewQaRequestDTO;
import com.chapssal_tteok.preview.domain.interviewqa.entity.InterviewQa;

public interface InterviewQaCommandService {

    InterviewQa createInterviewQa(Long interviewId, InterviewQaRequestDTO.CreateInterviewQaDTO request);

    InterviewQa updateInterviewQa(Long interviewId, Long interviewQaId, InterviewQaRequestDTO.UpdateInterviewQaDTO request);

    void deleteInterviewQa(Long interviewId, Long interviewQaId);
}
