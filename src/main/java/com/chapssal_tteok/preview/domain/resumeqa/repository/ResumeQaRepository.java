package com.chapssal_tteok.preview.domain.resumeqa.repository;

import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.domain.resumeqa.entity.ResumeQa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeQaRepository extends JpaRepository<ResumeQa, Long> {

    // 특정 자기소개서에 속한 문답을 번호순으로 조회
    List<ResumeQa> findAllByResumeOrderByOrderIndexAsc(Resume resume);

    // 특정 자기소개서의 최대 번호 조회 (number 중 가장 큰 값)
    @Query("SELECT COALESCE(MAX(q.orderIndex), 0) FROM ResumeQa q WHERE q.resume = :resume")
    Integer findMaxOrderIndexByResume(Resume resume);
}
