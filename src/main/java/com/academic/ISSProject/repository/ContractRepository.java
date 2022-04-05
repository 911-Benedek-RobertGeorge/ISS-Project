package com.academic.ISSProject.repository;

import com.academic.ISSProject.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract,Long> {
}
