package com.chapssal_tteok.preview.domain.interview.service;

import com.chapssal_tteok.preview.domain.interview.dto.InterviewRequestDTO;
import com.chapssal_tteok.preview.domain.interview.entity.Interview;

public interface InterviewCommandService {

    Interview createInterview(InterviewRequestDTO.CreateInterviewDTO request);

    Interview updateInterview(Long interviewId, InterviewRequestDTO.UpdateInterviewDTO request);

    void deleteInterview(Long interviewId);
}
