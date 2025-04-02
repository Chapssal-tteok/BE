package com.chapssal_tteok.preview.domain.resumeqa.converter;

import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.domain.resumeqa.dto.ResumeQaRequestDTO;
import com.chapssal_tteok.preview.domain.resumeqa.dto.ResumeQaResponseDTO;
import com.chapssal_tteok.preview.domain.resumeqa.entity.ResumeQa;

public class ResumeQaConverter {

    public static ResumeQaResponseDTO.CreateResumeQaResultDTO toCreateResumeQaResultDTO(ResumeQa resumeQa) {

        return ResumeQaResponseDTO.CreateResumeQaResultDTO.builder()
                .resumeQaId(resumeQa.getId())
                .resumeId(resumeQa.getResume().getId())
                .number(resumeQa.getNumber())
                .question(resumeQa.getQuestion())
                .answer(resumeQa.getAnswer())
                .analysis(resumeQa.getAnalysis())
                .createdAt(resumeQa.getCreatedAt())
                .build();
    }

    public static ResumeQaResponseDTO.ResumeQaDTO toResumeQaDTO(ResumeQa resumeQa) {

        return ResumeQaResponseDTO.ResumeQaDTO.builder()
                .resumeQaId(resumeQa.getId())
                .resumeId(resumeQa.getResume().getId())
                .number(resumeQa.getNumber())
                .question(resumeQa.getQuestion())
                .answer(resumeQa.getAnswer())
                .analysis(resumeQa.getAnalysis())
                .createdAt(resumeQa.getCreatedAt())
                .updatedAt(resumeQa.getUpdatedAt())
                .build();
    }

    public static ResumeQa toResumeQa(ResumeQaRequestDTO.CreateResumeQaDTO request, Resume resume) {

        return ResumeQa.builder()
                .resume(resume)
                .number(request.getNumber())
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .analysis(request.getAnalysis())
                .build();
    }
}
