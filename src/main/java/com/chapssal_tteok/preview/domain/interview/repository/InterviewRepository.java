package com.chapssal_tteok.preview.domain.interview.repository;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

}
