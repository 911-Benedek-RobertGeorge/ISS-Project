package com.academic.ISSProject.service.implementation;


import com.academic.ISSProject.domain.*;
import com.academic.ISSProject.domain.dto.SimpleSpecializationDto;
import com.academic.ISSProject.domain.dto.SpecializationDto;
import com.academic.ISSProject.repository.CurriculumRepository;
import com.academic.ISSProject.repository.EnrollRepository;
import com.academic.ISSProject.repository.SpecializationRepository1;
import com.academic.ISSProject.repository.StudentRepository;
import com.academic.ISSProject.service.IEnrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class EnrollService implements IEnrollService {

    private final SpecializationRepository1 specializationRepository;
    private final CurriculumRepository curriculumRepository;
    private final StudentRepository studentRepository;
    private final EnrollRepository enrollRepository;

    @Autowired
    public EnrollService(SpecializationRepository1 specializationRepository, CurriculumRepository curriculumRepository, StudentRepository studentRepository, EnrollRepository enrollRepository) {
        this.specializationRepository = specializationRepository;
        this.curriculumRepository = curriculumRepository;
        this.studentRepository = studentRepository;
        this.enrollRepository = enrollRepository;
    }


    @Override
    public List<SpecializationDto> getAllSpecializations(Long studentId) {
        List<Specialization> specializations = specializationRepository.findAll();
        List<SpecializationDto> spec = new ArrayList<>();
        specializations.forEach(specialization -> spec.add(new SpecializationDto(
                new SimpleSpecializationDto(specialization.getId(), specialization.getName(), specialization.getYearsOfStudy()),
                getCurrentYearForSpecializationForStudent(studentId, specialization))));
        return spec;
    }

    @Override
    public List<Specialization> getSpecializations() {
        return specializationRepository.findAll();
    }

    private Integer getCurrentYearForSpecializationForStudent(long studentId, Specialization specializations) {
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
                                            (year) -> {
                                                result.set(year + 1);
                                            },
                                            () -> {
                                                result.set(0);
                                            }
                                    );
                        },
                        () -> {
                            result.set(0);
                        }
                );

        return result.get();
    }

    @Override
    public List<Curriculum> getCurriculumsOfSpecialization(long specializationId) {
        return specializationRepository.getById(specializationId).getCurriculums();
    }


    @Override
    public List<Course> getCoursesOfCurriculum(Long currId) {
        return curriculumRepository.getById(currId).getCourses();
    }

    @Override
    public Boolean checkIfEnrolled(Long studentId, Long specializationId) {
         if(checkIfEnrolled(studentId,specializationId) ){
            throw new RuntimeException("The student is already enrolled at this specialiation");
        }
        Long exist =  specializationRepository.checkIfEnrolled(studentId,specializationId);
        log.info("check if enrolled returned : " + exist);
        if( exist != null)
        {
            return true;
        }
        return false;

    }

    @Override
    public Enroll enrollToSpecialization(Long studentId, Long specId){

        Student student = studentRepository.getById(studentId);
        if(student == null) {
            throw new NoSuchElementException("Student with id " + studentId + " was not found");

        }
        Specialization specialization = specializationRepository.getById(specId);
        if (specialization == null){
            throw new NoSuchElementException("Specialization with id " + specId + " was not found");

        }
        Enroll enroll = new Enroll(0L,student,specialization);
        return enrollRepository.save(enroll);
    }

}
