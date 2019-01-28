package com.vukat.studentresource.web;

import com.vukat.studentresource.domain.University;
import com.vukat.studentresource.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/university")
@CrossOrigin
public class UniversityController {

    @Autowired
    private UniversityService universityService;


    @GetMapping(value = "/takeAllUniversities")
    public ResponseEntity<?> takeAllUniversities(){

        ArrayList<University> liste = universityService.findUniversities();
        return new ResponseEntity(liste, HttpStatus.OK);
    }


}
