package com.vukat.studentresource.service;

import com.vukat.studentresource.domain.Course;
import com.vukat.studentresource.domain.Faculty;
import com.vukat.studentresource.exception.CourseNotFoundException;
import com.vukat.studentresource.repository.CourseRepository;
import com.vukat.studentresource.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Course> findCoursesById(Long id)
    {
        Optional<Faculty> faculty = facultyRepository.findById(id);

        // check faculty is null ?

        return faculty.get().getCourses();

    }

    public Course findCourseById(Long id){

       try{
           Optional<Course> course = courseRepository.findById(id);


           // check null

           return course.get();

       }catch (Exception ex){
           throw new CourseNotFoundException("Course didn't find");
       }
    }

    public void saveCourse(Course course){
        this.courseRepository.save(course);
    }

}
