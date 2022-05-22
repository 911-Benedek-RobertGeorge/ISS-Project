package com.academic.ISSProject.repository;

import com.academic.ISSProject.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query("SELECT c.id FROM  Course c WHere c.teacherId = :teacherId and c.required = 'OPTIONAL'")
    List<Long> getAllOptionalCoursesByTeacherId(@Param("teacherId") Long teacherId);
}
