package com.salesapi.demo.controller;

import com.salesapi.demo.NotFoundException;
import com.salesapi.demo.model.Course;
import com.salesapi.demo.model.University;
import com.salesapi.demo.repository.CourseRepository;
import com.salesapi.demo.repository.UniversityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "university")//localhost/university
public class UniversityController {

    private final UniversityRepository universityRepository;
    private final CourseRepository courseRepository;

    public UniversityController(UniversityRepository universityRepository, CourseRepository courseRepository) {
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    private List<University> getAllUniversities(){
        return universityRepository.findAll();
    }

    @GetMapping(value = "{id}")
    private University findOneById(@PathVariable Long id){
        University university = universityRepository.findById(id).orElseThrow(()->new NotFoundException("No university with id" + id + "was found"));
//        if(university.isPresent()){
            return university;
//        }else{
//            return null;
//        }
    }

    @GetMapping(value = "{id}/courses")
    public List<Course> findCoursesByUniversityId(@PathVariable Long id){
        return courseRepository.findByUniversityId(id);
    }

}
