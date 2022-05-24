package com.academic.ISSProject.service.implementation;

import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Curriculum;
import com.academic.ISSProject.domain.dto.CourseDto;
import com.academic.ISSProject.domain.enums.Required;
import com.academic.ISSProject.repository.CourseRepository;
import com.academic.ISSProject.repository.CurriculumRepository;
import com.academic.ISSProject.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class CourseService implements ICourseService {

    private final CourseRepository courseRepository;
    private final CurriculumRepository curriculumRepository;

    public CourseService(CourseRepository courseRepository, CurriculumRepository curriculumRepository) {
        this.courseRepository = courseRepository;
        this.curriculumRepository = curriculumRepository;
    }

    @Override
    public List<Course> findAll() {
        log.info("Fetching all courses\n");
        return courseRepository.findAll();
    }

    @Override
    public Course findById(long id) {
        log.info("Get the course with id " + id + "\n");
        Optional<Course> opt = courseRepository.findById(id);
        if(opt.isPresent())
            return opt.get();
        else
            return null;
    }

    @Override
    public Course save(CourseDto courseInfo) {
        log.info("Saving a new course \n");

        Course newCourse = new Course();
        newCourse.setCourseName(courseInfo.getCourseName());
        newCourse.setCredits(courseInfo.getCredits());
        newCourse.setMaximumStudents(courseInfo.getMaximumStudents());
        newCourse.setTeacherId(courseInfo.getTeacherId());
        newCourse.setFollowers(courseInfo.getFollowers());
        newCourse.setRequired(Required.MANDATORY);

        Curriculum curriculum = this.curriculumRepository.getById(courseInfo.getCurriculum_id());

        newCourse.setCurriculum(curriculum);

        return courseRepository.save(newCourse);
    }

    @Override
    public void deleteById(long id) {
        log.info("Delete the course with id " + id + "\n");
        courseRepository.deleteById(id);
    }


}
