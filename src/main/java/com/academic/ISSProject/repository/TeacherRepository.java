package com.academic.ISSProject.repository;

import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

   /* @Query("select Teacher from Teacher t Where t.userInfo.id =: id")
    Teacher findByUsername(@Param("id") Long id);*/
}
