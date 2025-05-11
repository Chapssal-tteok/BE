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
                .company(resume.getCompany())
                .position(resume.getPosition())
                .createdAt(resume.getCreatedAt())
                .build();
    }

    public static ResumeResponseDTO.ResumeDTO toResumeDTO(Resume resume) {

        return ResumeResponseDTO.ResumeDTO.builder()
                .resumeId(resume.getId())
                .username(resume.getUser().getUsername())
                .title(resume.getTitle())
                .company(resume.getCompany())
                .position(resume.getPosition())
                .createdAt(resume.getCreatedAt())
                .updatedAt(resume.getUpdatedAt())
                .build();
    }

    public static Resume toResume(ResumeRequestDTO.CreateResumeDTO request, User user) {

        return Resume.builder()
                .user(user)
                .title(request.getTitle())
                .company(request.getCompany())
                .position(request.getPosition())
                .build();
    }
}
