package com.chapssal_tteok.preview.global.client;

import com.chapssal_tteok.preview.domain.interviewqa.dto.InterviewQaRequestDTO;
import com.chapssal_tteok.preview.domain.resumeqa.dto.ResumeQaRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AiClient {

    private final WebClient aiWebClient;

    public String analyzeResumeQa(ResumeQaRequestDTO.AnalyzeResumeQaDTO req) {
        Map<String, String> body = Map.of(
                "question", req.getQuestion(),
                "resume", req.getResume(),
                "company", req.getCompany(),
                "position", req.getPosition()
        );

        return aiWebClient.post()
                .uri("/interview/analyze-resume")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, List<String>>>() {})
                .map(res -> {
                    List<String> feedbackList = res.get("feedback");
                    return (feedbackList != null && !feedbackList.isEmpty()) ? String.join("\n", feedbackList) : null;
                })
                .block();
    }

    public String generateQuestion(InterviewQaRequestDTO.GenerateQuestionDTO req) {
        Map<String, Object> body = Map.of(
                "company", req.getCompany(),
                "position", req.getPosition(),
                "resumeContent", req.getResumeContent()
        );

        return aiWebClient.post()
                .uri("/interview/generate-qas")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, List<String>>>() {})
                .map(res -> {
                    List<String> questions = res.get("questions");
                    return (questions != null && !questions.isEmpty()) ? String.join("\n", questions) : null;
                })
                .block();
    }

    public String generateFollowUp(InterviewQaRequestDTO.GenerateFollowUpDTO req) {
        Map<String, String> body = Map.of(
                "question", req.getQuestion(),
                "answer", req.getAnswer()
        );

        return aiWebClient.post()
                .uri("/interview/follow-up")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, List<String>>>() {})
                .map(res -> {
                    List<String> followUps = res.get("followUps");
                    return (followUps != null && !followUps.isEmpty()) ? String.join("\n", followUps) : null;
                })
                .block();
    }

    public String analyzeAnswer(String question, String answer, String resume) {
        Map<String, String> body = Map.of(
                "question", question,
                "answer", answer,
                "resume", resume
        );

        return aiWebClient.post()
                .uri("/interview/analyze-answer")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {})
                .map(res -> res.getOrDefault("analysis", null))
                .block();
    }
}
