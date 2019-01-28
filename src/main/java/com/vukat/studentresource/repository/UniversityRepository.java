package com.vukat.studentresource.repository;

import com.vukat.studentresource.domain.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends CrudRepository<University, Long> {


    @Override
    Iterable<University> findAll();

    @Override
    Optional<University> findById(Long aLong);
}
