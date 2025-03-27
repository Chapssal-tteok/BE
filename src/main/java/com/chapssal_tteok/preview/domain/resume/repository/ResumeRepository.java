package com.chapssal_tteok.preview.domain.resume.repository;

import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
