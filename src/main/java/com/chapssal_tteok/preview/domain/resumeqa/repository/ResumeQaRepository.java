package com.chapssal_tteok.preview.domain.resumeqa.repository;

import com.chapssal_tteok.preview.domain.resumeqa.entity.ResumeQa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeQaRepository extends JpaRepository<ResumeQa, Long> {

}
