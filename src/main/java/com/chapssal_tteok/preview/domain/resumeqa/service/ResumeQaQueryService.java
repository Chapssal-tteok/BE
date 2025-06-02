package com.chapssal_tteok.preview.domain.resumeqa.service;

import com.chapssal_tteok.preview.domain.resumeqa.entity.ResumeQa;

import java.util.List;

public interface ResumeQaQueryService {

    ResumeQa getResumeQaById(Long resumeQaId);

    List<ResumeQa> getOrderedQasByResumeId(Long resumeId);
}
