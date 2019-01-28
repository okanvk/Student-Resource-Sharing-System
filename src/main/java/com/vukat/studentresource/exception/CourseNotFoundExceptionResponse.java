package com.vukat.studentresource.exception;

public class CourseNotFoundExceptionResponse {

    private String courseNotFound;

    public CourseNotFoundExceptionResponse(String courseNotFound){
        this.courseNotFound = courseNotFound;
    }

    public String getCourseNotFound() {
        return courseNotFound;
    }

    public void setCourseNotFound(String courseNotFound) {
        this.courseNotFound = courseNotFound;
    }
}