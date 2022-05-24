package com.academic.ISSProject.repository;

import com.academic.ISSProject.domain.Contract;
import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract,Long> {
    @Query("SELECT c.student FROM Contract c WHere c.curriculum.id = :curriculumId")
    List<Student> getAllStudentsOfACurriculum(@Param("curriculumId") Long teacherId);

}
