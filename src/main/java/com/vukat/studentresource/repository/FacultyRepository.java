package com.vukat.studentresource.repository;

import com.vukat.studentresource.domain.Faculty;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FacultyRepository extends CrudRepository<Faculty,Long> {

    @Override
    Optional<Faculty> findById(Long id);
}
