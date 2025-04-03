package com.chapssal_tteok.preview.domain.interviewqa.repository;

import com.chapssal_tteok.preview.domain.interviewqa.entity.InterviewQa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewQaRepository extends JpaRepository<InterviewQa, Long> {

}
