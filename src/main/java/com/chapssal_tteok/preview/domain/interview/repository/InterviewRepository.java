package com.chapssal_tteok.preview.domain.interview.repository;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    Optional<Interview> findById(Long interviewId);

    List<Interview> findAllByUserId(Long userId);
}
