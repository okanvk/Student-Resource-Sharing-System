package com.vukat.studentresource.web;

import com.vukat.studentresource.domain.Course;
import com.vukat.studentresource.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/takeCourses/{facultyId}")
    public ResponseEntity<?>takeCoursesById(@PathVariable long facultyId){

        List<Course> liste = courseService.findCoursesById(facultyId);

        return new ResponseEntity(liste, HttpStatus.OK);

    }

    @GetMapping("/takeCourse/{courseId}")
    public ResponseEntity<?> takeCourseById(@PathVariable long courseId){

        Course course = courseService.findCourseById(courseId);

        return new ResponseEntity<>(course,HttpStatus.OK);

    }

}
