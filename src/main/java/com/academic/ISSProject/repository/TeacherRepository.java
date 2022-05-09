package com.academic.ISSProject.repository;

import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
