package com.chapssal_tteok.preview.domain.interviewqa.repository;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.interviewqa.entity.InterviewQa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewQaRepository extends JpaRepository<InterviewQa, Long> {

    // 특정 면접에 속한 Q&A를 순서대로 조회
    List<InterviewQa> findAllByInterviewOrderByOrderIndexAsc(Interview interview);

    // 특정 면접의 최대 순서를 조회 (orderIndex 중 가장 큰 값)
    @Query("SELECT COALESCE(MAX(q.orderIndex), 0) FROM InterviewQa q WHERE q.interview = :interview")
    Integer findMaxOrderIndexByInterview(Interview interview);
}
