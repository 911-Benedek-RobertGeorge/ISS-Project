package com.academic.ISSProject.service.implementation;


import com.academic.ISSProject.domain.Specialization;
import com.academic.ISSProject.domain.dto.SpecializationDto;
import com.academic.ISSProject.repository.CurriculumRepository;
import com.academic.ISSProject.repository.SpecializationRepository1;
import com.academic.ISSProject.service.IEnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollService implements IEnrollService {

    private final SpecializationRepository1 specializationRepository;
    private final CurriculumRepository curriculumRepository;


    @Autowired
    public EnrollService(SpecializationRepository1 specializationRepository, CurriculumRepository curriculumRepository) {
        this.specializationRepository = specializationRepository;
        this.curriculumRepository = curriculumRepository;
    }


    @Override
    public List<SpecializationDto> getAllSpecializations(){
        List<Specialization> specializations =  specializationRepository.findAll();
        List<SpecializationDto> spec = new ArrayList<>();
        specializations.forEach(specialization -> spec.add(new SpecializationDto(specialization,0)));
        return spec;
    }
}
