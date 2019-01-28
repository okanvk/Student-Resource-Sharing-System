package com.vukat.studentresource.service;

import com.vukat.studentresource.domain.Course;
import com.vukat.studentresource.domain.Resource;
import com.vukat.studentresource.domain.User;
import com.vukat.studentresource.exception.ResourceNotFoundException;
import com.vukat.studentresource.repository.ResourceRepository;
import com.vukat.studentresource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Resource> findResourcesByCourseId(Long id){

        Course course = courseService.findCourseById(id);

        return course.getResources();

    }

    public Resource findResourceById(Long id,String username){

            Optional<Resource> optionalResource = resourceRepository.findById(id);
            Resource resource;

            if(optionalResource.isPresent()){
                resource = optionalResource.get();
            }
            else{
                throw new ResourceNotFoundException("Resource didn't exist");
            }
            User user = userRepository.findByUsername(username);

            if(user != resource.getUser()){
                throw new ResourceNotFoundException("Resource don't belong this user");
            }

            return resource;

    }

    public Resource addResource(Resource resource,Long id,String username){

        User user = userRepository.findByUsername(username);

        Course course = courseService.findCourseById(id);

        resource.setUser(user);

        resource.setCourse(course);

        resourceRepository.save(resource);

        return resource;

    }

    public List<Resource> findResourcesByUsername(String username){

        User user = userRepository.findByUsername(username);

        return user.getResources();
    }

    public void deleteResource(long id){
        try{

            Optional<Resource> deleted_resource = resourceRepository.findById(id);
            resourceRepository.delete(deleted_resource.get());

        }catch (Exception ex){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    public Resource updateResource(Resource resource){

       Optional<Resource> main_resource = resourceRepository.findById(resource.getId());

        if(main_resource.isPresent()){
           Resource updated_resource = main_resource.get();

           updated_resource.setDescription(resource.getDescription());
           updated_resource.setLink(resource.getLink());
           updated_resource.setType(resource.getType());

           return resourceRepository.save(updated_resource);
        }
        else{
            System.out.println("no");
            throw new ResourceNotFoundException("Resource not found");
        }
    }
}
