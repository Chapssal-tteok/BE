package com.chapssal_tteok.preview.domain.interview.repository;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    @Query("SELECT i FROM Interview i LEFT JOIN FETCH i.interviewQas WHERE i.id = :interviewId")
    Optional<Interview> findWithQasById(Long interviewId);
}
