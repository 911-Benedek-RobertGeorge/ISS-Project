package com.academic.ISSProject.repository;

import com.academic.ISSProject.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
