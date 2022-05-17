package com.academic.ISSProject.repository;


import com.academic.ISSProject.domain.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpecializationRepository1 extends JpaRepository<Specialization,Long> {
}
