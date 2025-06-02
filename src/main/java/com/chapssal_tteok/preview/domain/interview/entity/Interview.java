package com.chapssal_tteok.preview.domain.interview.entity;

import com.chapssal_tteok.preview.domain.interviewqa.entity.InterviewQa;
import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.domain.user.entity.User;
import com.chapssal_tteok.preview.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Interview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    private String title;

    private String company;

    private String position;

    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InterviewQa> interviewQas;

    public void updateTitle(String title) {
        this.title = title;
    }
}
