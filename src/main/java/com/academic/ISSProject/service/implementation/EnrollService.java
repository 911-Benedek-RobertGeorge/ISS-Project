package com.academic.ISSProject.service.implementation;


import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Curriculum;
import com.academic.ISSProject.domain.Specialization;
import com.academic.ISSProject.domain.dto.CurriculumDto;
import com.academic.ISSProject.domain.dto.SimpleSpecializationDto;
import com.academic.ISSProject.domain.dto.SpecializationDto;
import com.academic.ISSProject.repository.CurriculumRepository;
import com.academic.ISSProject.repository.SpecializationRepository1;
import com.academic.ISSProject.service.IEnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    public List<SpecializationDto> getAllSpecializations(Long studentId){
        List<Specialization> specializations =  specializationRepository.findAll();
        List<SpecializationDto> spec = new ArrayList<>();
        specializations.forEach(specialization -> spec.add(new SpecializationDto(
                new SimpleSpecializationDto(specialization.getId(),specialization.getName(),specialization.getYearsOfStudy()),
                getCurrentYearForSpecializationForStudent(studentId, specialization))));
        return spec;
    }

    @Override
    public List<Specialization> getSpecializations(){
        return specializationRepository.findAll();
    }

    private Integer getCurrentYearForSpecializationForStudent(long studentId, Specialization specializations){
        AtomicReference<Integer> result = new AtomicReference<>();

        specializations.getStudents()
                .stream()
                .filter(value2 -> value2.getId().equals(studentId))
                .findFirst()
                .ifPresentOrElse(
                        (value3) -> {
                            value3.getContracts().stream()
                                    .map(contract -> contract.getCurriculum())
                                    .map(curriculum -> curriculum.getYear())
                                    .max(Integer::compare)
                                    .ifPresentOrElse(
                                        (year) -> {result.set(year+1);},
                                        () -> {result.set(0);}
                                    );
                            },
                            () -> {result.set(0);}
                );

        return result.get();
    }

    @Override
    public List<CurriculumDto> getCurriculumsOfSpecialization(long specializationId){
        Specialization mySpecialization = specializationRepository.getById(specializationId);
        List<Curriculum> myCurriculums = mySpecialization.getCurriculums();
        List<CurriculumDto> resultCurriculumsDto = new ArrayList<>();
        myCurriculums.forEach((value) -> {
            resultCurriculumsDto.add(new CurriculumDto(value.getId(), value.getYear(), value.getCurriculumName(), value.getLanguage()));
        });
        return  resultCurriculumsDto;
    }


    @Override
    public List<Course> getCoursesOfCurriculum(Long currId){
         return curriculumRepository.getById(currId).getCourses();
    }

}
