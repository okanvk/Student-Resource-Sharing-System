package com.vukat.studentresource.service;

import com.vukat.studentresource.domain.Faculty;
import com.vukat.studentresource.domain.University;
import com.vukat.studentresource.repository.FacultyRepository;
import com.vukat.studentresource.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private UniversityRepository universityRepository;

    public List<Faculty> findFacultiesById(Long id){

        Optional<University> university =(universityRepository.findById(id));

        // check null ?

        return university.get().getFaculties();
    }

    public void saveFaculty(Faculty faculty){
        this.facultyRepository.save(faculty);
    }

}
