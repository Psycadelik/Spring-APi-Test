package com.salesapi.demo.controller;


import com.salesapi.demo.model.Course;
import com.salesapi.demo.repository.CourseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "course")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @GetMapping
    private List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    @GetMapping(value = "{id}")
    private Course findOneById(@PathVariable Long id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return course.get();
        }else{
            return null;
        }
    }

}
