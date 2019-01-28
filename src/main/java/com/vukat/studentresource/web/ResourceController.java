package com.vukat.studentresource.web;

import com.vukat.studentresource.domain.Resource;
import com.vukat.studentresource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/resource")
@CrossOrigin()
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/getResource/{courseId}")
    public ResponseEntity<?> getResourcesById(@PathVariable long courseId){

        List<Resource> resources = resourceService.findResourcesByCourseId(courseId);

        return new ResponseEntity<>(resources, HttpStatus.OK);

    }

    @PutMapping("/addResource/{courseId}")
    public ResponseEntity<?> addResource(@RequestBody Resource resource, @PathVariable long courseId, Principal principal){

        String username = principal.getName();

        Resource added_resource = resourceService.addResource(resource,courseId,username);

        return new ResponseEntity<>(added_resource,HttpStatus.OK);
    }


    @GetMapping(value = "/getResources")
    public ResponseEntity<?> getResourcesByUser(Principal principal){

        String username = principal.getName();

        List<Resource> resources = resourceService.findResourcesByUsername(username);

        return new ResponseEntity<>(resources,HttpStatus.OK);
    }

    @DeleteMapping("/deleteResource/{resourceId}")
    public ResponseEntity<?> deleteResourceByResourceId(@PathVariable long resourceId){

        resourceService.deleteResource(resourceId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getResourceById/{resourceId}")
    public ResponseEntity<?> getResourceById(@PathVariable long resourceId,Principal principal){

        String username = principal.getName();

        Resource resource = resourceService.findResourceById(resourceId,username);

        return new ResponseEntity<>(resource,HttpStatus.OK);
    }

    @PutMapping(value = "/updateResource")
    public ResponseEntity<?> updateResource(@RequestBody Resource resource){
        Resource updateResource = resourceService.updateResource(resource);
        return new ResponseEntity<>(updateResource,HttpStatus.OK);
    }
}
