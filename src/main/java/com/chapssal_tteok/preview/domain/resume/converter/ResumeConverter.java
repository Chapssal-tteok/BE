package com.chapssal_tteok.preview.domain.resume.converter;

import com.chapssal_tteok.preview.domain.resume.dto.ResumeRequestDTO;
import com.chapssal_tteok.preview.domain.resume.dto.ResumeResponseDTO;
import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.domain.resumeqa.dto.ResumeQaResponseDTO;
import com.chapssal_tteok.preview.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ResumeConverter {

    public static ResumeResponseDTO.CreateResumeResultDTO toCreateResumeResultDTO(Resume resume) {

        return ResumeResponseDTO.CreateResumeResultDTO.builder()
                .resumeId(resume.getId())
                .username(resume.getUser().getUsername())
                .title(resume.getTitle())
                .createdAt(resume.getCreatedAt())
                .build();
    }

    public static ResumeResponseDTO.ResumeDTO toResumeDTO(Resume resume) {

        List<ResumeQaResponseDTO.ResumeQaDTO> resumeQaDTOs = resume.getResumeQas().stream()
                .map(qa -> ResumeQaResponseDTO.ResumeQaDTO.builder()
                        .resumeQaId(qa.getId())
                        .resumeId(qa.getResume().getId())
                        .number(qa.getNumber())
                        .question(qa.getQuestion())
                        .answer(qa.getAnswer())
                        .analysis(qa.getAnalysis())
                        .createdAt(qa.getCreatedAt())
                        .updatedAt(qa.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());

        return ResumeResponseDTO.ResumeDTO.builder()
                .resumeId(resume.getId())
                .username(resume.getUser().getUsername())
                .title(resume.getTitle())
                .resumeQas(resumeQaDTOs)
                .createdAt(resume.getCreatedAt())
                .updatedAt(resume.getUpdatedAt())
                .build();
    }

    public static ResumeResponseDTO.SimpleResumeDTO toSimpleResumeDTO(Resume resume) {

        return ResumeResponseDTO.SimpleResumeDTO.builder()
                .resumeId(resume.getId())
                .username(resume.getUser().getUsername())
                .title(resume.getTitle())
                .createdAt(resume.getCreatedAt())
                .updatedAt(resume.getUpdatedAt())
                .build();
    }

    public static Resume toResume(ResumeRequestDTO.CreateResumeDTO request, User user) {

        return Resume.builder()
                .user(user)
                .title(request.getTitle())
                .build();
    }
}
