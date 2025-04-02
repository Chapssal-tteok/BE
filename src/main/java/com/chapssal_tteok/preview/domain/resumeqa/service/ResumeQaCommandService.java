package com.chapssal_tteok.preview.domain.resumeqa.service;

import com.chapssal_tteok.preview.domain.resumeqa.dto.ResumeQaRequestDTO;
import com.chapssal_tteok.preview.domain.resumeqa.entity.ResumeQa;

public interface ResumeQaCommandService {

    ResumeQa createResumeQa(ResumeQaRequestDTO.CreateResumeQaDTO request);

    ResumeQa updateResumeQa(Long resumeQaId, ResumeQaRequestDTO.UpdateResumeQaDTO request);

    void deleteResumeQa(Long resumeId);
}
