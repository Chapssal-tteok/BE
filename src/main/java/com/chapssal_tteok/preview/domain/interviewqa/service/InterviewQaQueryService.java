package com.chapssal_tteok.preview.domain.interviewqa.service;

import com.chapssal_tteok.preview.domain.interviewqa.entity.InterviewQa;

import java.util.List;

public interface InterviewQaQueryService {

    InterviewQa getInterviewQaById(Long interviewQaId);

    List<InterviewQa> getOrderedQasByInterviewId(Long interviewId);
}
