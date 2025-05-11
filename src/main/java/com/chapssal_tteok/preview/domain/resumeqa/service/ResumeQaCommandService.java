package com.chapssal_tteok.preview.domain.resumeqa.service;

import com.chapssal_tteok.preview.domain.resumeqa.dto.ResumeQaRequestDTO;
import com.chapssal_tteok.preview.domain.resumeqa.entity.ResumeQa;

public interface ResumeQaCommandService {

    ResumeQa createResumeQa(Long resumeId, ResumeQaRequestDTO.CreateResumeQaDTO request);

    ResumeQa updateResumeQa(Long resumeId, Long resumeQaId, ResumeQaRequestDTO.UpdateResumeQaDTO request);

    ResumeQa analyzeResumeQa(Long resumeId, Long qaId, ResumeQaRequestDTO.AnalyzeResumeQaDTO request);

    void deleteResumeQa(Long resumeId, Long resumeQaId);
}
