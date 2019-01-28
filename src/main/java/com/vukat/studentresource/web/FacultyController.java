package com.vukat.studentresource.web;

import com.vukat.studentresource.domain.Faculty;
import com.vukat.studentresource.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/faculty")
@CrossOrigin
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping(value = "/takeFaculty/{universityID}")
    public ResponseEntity<?> takeFacultiesById(@PathVariable long universityID){

        List<Faculty> liste = facultyService.findFacultiesById(universityID);
        return new ResponseEntity(liste, HttpStatus.OK);


    }


}
