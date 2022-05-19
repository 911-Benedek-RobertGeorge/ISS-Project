package com.academic.ISSProject.repository;


import com.academic.ISSProject.domain.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SpecializationRepository1 extends JpaRepository<Specialization,Long> {

     @Query("SELECT e.id FROM  Enroll e WHere e.student.id = :sId and e.specialization.id = :specId")
     Long checkIfEnrolled(@Param("sId") Long studentId, @Param("specId") Long specId);
}
