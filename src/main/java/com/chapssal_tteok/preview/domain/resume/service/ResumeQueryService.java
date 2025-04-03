package com.chapssal_tteok.preview.domain.resume.service;

import com.chapssal_tteok.preview.domain.resume.entity.Resume;

public interface ResumeQueryService {

    Resume getResumeById(Long resumeId);
}
