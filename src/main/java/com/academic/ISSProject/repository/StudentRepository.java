package com.academic.ISSProject.repository;

import com.academic.ISSProject.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
