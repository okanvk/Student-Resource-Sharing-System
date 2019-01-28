package com.vukat.studentresource.service;

import com.vukat.studentresource.domain.University;
import com.vukat.studentresource.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UniversityService {


    @Autowired
    private UniversityRepository universityRepository;

    public void addUniversity(University university){
        universityRepository.save(university);
    }

    public ArrayList<University> findUniversities(){
        return (ArrayList<University>) universityRepository.findAll();
    }

}
