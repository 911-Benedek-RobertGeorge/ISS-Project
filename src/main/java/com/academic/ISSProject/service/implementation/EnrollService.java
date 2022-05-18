package com.academic.ISSProject.service.implementation;


import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Curriculum;
import com.academic.ISSProject.domain.Specialization;
import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.domain.dto.SimpleSpecializationDto;
import com.academic.ISSProject.domain.dto.SpecializationDto;
import com.academic.ISSProject.repository.CurriculumRepository;
import com.academic.ISSProject.repository.SpecializationRepository1;
import com.academic.ISSProject.repository.StudentRepository;
import com.academic.ISSProject.service.IEnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EnrollService implements IEnrollService {

    private final SpecializationRepository1 specializationRepository;
    private final CurriculumRepository curriculumRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public EnrollService(SpecializationRepository1 specializationRepository, CurriculumRepository curriculumRepository, StudentRepository studentRepository) {
        this.specializationRepository = specializationRepository;
        this.curriculumRepository = curriculumRepository;
        this.studentRepository = studentRepository;
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
    public List<Curriculum> getCurriculumsOfSpecialization(long specializationId){
        return  specializationRepository.getById(specializationId).getCurriculums();
    }


    @Override
    public List<Course> getCoursesOfCurriculum(Long currId){
         return curriculumRepository.getById(currId).getCourses();
    }
    @Override
    public Boolean checkIfEnrolled(Long studentId, Long specializationId) {
        Student student = studentRepository.getById(studentId);
        if (student != null) {


            for (Specialization specialization : student.getSpecializations()) {
                if (specialization.getId() == specializationId)
                    return true;
            }
            return false;
        }else{
            throw new NoSuchElementException("Student with id " + studentId + " was not found ");
        }
    }

}
