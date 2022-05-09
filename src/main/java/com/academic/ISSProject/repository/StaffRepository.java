package com.academic.ISSProject.repository;

import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,Long> {
}
