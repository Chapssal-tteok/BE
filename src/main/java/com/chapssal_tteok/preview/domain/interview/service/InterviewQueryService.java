package com.chapssal_tteok.preview.domain.interview.service;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;

public interface InterviewQueryService {

    Interview getInterviewById(Long interviewId);
}
