package com.chapssal_tteok.preview.domain.interviewqa.entity;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InterviewQa extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_qa_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id")
    private Interview interview;

    @Column(nullable = false)
    private Integer orderIndex;

    @Column(columnDefinition = "TEXT")
    private String question;

    @Column(columnDefinition = "TEXT")
    private String questionAudio;

    @Column(columnDefinition = "TEXT")
    private String answer;

    @Column(columnDefinition = "TEXT")
    private String answerAudio;

    @Column(columnDefinition = "TEXT")
    private String analysis;

    public void updateOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public void updateQuestion(String question) {
        this.question = question;
    }

    public void updateQuestionAudio(String questionAudio) {
        this.questionAudio = questionAudio;
    }

    public void updateAnswer(String answer) {
        this.answer = answer;
    }

    public void updateAnswerAudio(String answerAudio) {
        this.answerAudio = answerAudio;
    }

    public void updateAnalysis(String analysis) {
        this.analysis = analysis;
    }
}
