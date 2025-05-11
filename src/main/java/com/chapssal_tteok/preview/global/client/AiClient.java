package com.chapssal_tteok.preview.global.client;

import com.chapssal_tteok.preview.domain.interviewqa.dto.InterviewQaRequestDTO;
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
}
