package com.academic.ISSProject.repository;

import com.academic.ISSProject.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade,Long> {
}
