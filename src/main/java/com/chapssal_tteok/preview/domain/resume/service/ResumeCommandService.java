package com.chapssal_tteok.preview.domain.resume.service;

import com.chapssal_tteok.preview.domain.resume.dto.ResumeRequestDTO;
import com.chapssal_tteok.preview.domain.resume.entity.Resume;

public interface ResumeCommandService {

    Resume createResume(ResumeRequestDTO.CreateResumeDTO request);

    Resume updateResume(Long resumeId, ResumeRequestDTO.UpdateResumeDTO request);

    void deleteResume(Long resumeId);
}
