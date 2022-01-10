package br.com.forum.service;

import br.com.forum.models.Course;
import br.com.forum.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private ICourseRepository courseRepository;

    public Course findByUuid(UUID courseUuid) {
        Optional<Course> course = courseRepository.findByUuid(courseUuid);

        if(course.isPresent()){
            return course.get();
        }
        return null;
    }
}
