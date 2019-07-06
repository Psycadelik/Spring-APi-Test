package com.salesapi.demo.controller;


import com.salesapi.demo.LimitExceedException;
import com.salesapi.demo.NotFoundException;
import com.salesapi.demo.model.Course;
import com.salesapi.demo.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "courses")
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
        Course course = courseRepository.findById(id).orElseThrow(()->new NotFoundException("No course with id " + id + " was found"));
//        if(course.isPresent()){
            return course;
//        }else{
//            return null;
//        }
    }

    @GetMapping(value = "{id}/add/{enrollment}")
    private Course findOneById(@PathVariable Long id, @PathVariable Integer enrollment){
        //Step 1. check if maximum enrollment exceeds 7
//        int enroll = Integer.valueOf(enrollment);

        int enrol = 0;
//        System.out.println("enrollment " + enrollment);  //Log request params

        if(enrollment <= 7){
            Course course =  courseRepository.findById(id).orElseThrow(()->new NotFoundException("No course with id " + id + " was found"));;
            enrol = enrollment + course.getMaximum_enrollment();

            course.setMaximum_enrollment(enrol);
            courseRepository.save(course);

            return course;

        }else{
            throw new LimitExceedException("Limit exceeded");
        }


    }

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseRepository.save(course);
    }

    @PatchMapping(value = "{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course){
        Course foundcourse = findOneById(id);

        foundcourse.setCourse_name(course.getCourse_name());
        foundcourse.setCourse_code(course.getCourse_code());
        foundcourse.setFaculty(course.getFaculty());
        foundcourse.setUniversity(course.getUniversity());
        foundcourse.setMaximum_enrollment(course.getMaximum_enrollment());

        return courseRepository.save(foundcourse);
    }

    @DeleteMapping(value = "{id}")
    public void deleteCourse(@PathVariable Long id){
        courseRepository.deleteById(id);
    }


}
