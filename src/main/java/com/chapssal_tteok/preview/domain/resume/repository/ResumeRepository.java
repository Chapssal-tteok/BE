package com.chapssal_tteok.preview.domain.resume.repository;

import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

    @Query("SELECT r FROM Resume r LEFT JOIN FETCH r.resumeQas WHERE r.id = :resumeId")
    Optional<Resume> findWithQasById(Long resumeId);
}
