package com.vukat.studentresource.repository;

import com.vukat.studentresource.domain.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course,Long> {

    @Override
    Iterable<Course> findAll();

    Optional<Course> findById(Long id);


}


