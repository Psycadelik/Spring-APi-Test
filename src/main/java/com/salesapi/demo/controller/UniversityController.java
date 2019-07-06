package com.salesapi.demo.controller;

import com.salesapi.demo.NotFoundException;
import com.salesapi.demo.model.Course;
import com.salesapi.demo.model.University;
import com.salesapi.demo.repository.CourseRepository;
import com.salesapi.demo.repository.UniversityRepository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public University createUniversity(@RequestBody University university){
        return universityRepository.save(university);
    }

    @PatchMapping(value = "{id}")
    public University updateUniversity(@PathVariable Long id, @RequestBody University university){
        University foundUniversity = findOneById(id);

        foundUniversity.setName(university.getName());
        foundUniversity.setLocation(university.getLocation());

        return universityRepository.save(foundUniversity);
    }

    @DeleteMapping(value = "{id}")
    public void deleteUniversity(@PathVariable Long id){
        universityRepository.deleteById(id);

        //softDelete
//        University university = findOneById(id);
//        universityRepository.setDeleted = true;
//        universityRepository.save(university);
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

    @GetMapping(value = "{id}/courses/{id}")
    public Course findOneById(@PathVariable Long id, @PathVariable Long courseId){
         Course course =  courseRepository.findById(courseId).orElseThrow(()->new NotFoundException("No course with id " + courseId + " was found"));

         return course;
    }

    @PostMapping(value = "{id}/courses")
    public Course createUniversityCourse(@PathVariable Long id,@RequestBody Course courses){
        return courseRepository.save(courses);
    }


    @PatchMapping(value = "{id}/courses/{id}")
    public Course updateCourse(@PathVariable Long id,@RequestBody Course course,@PathVariable Long courseId){
        Course foundCourse =  courseRepository.findById(courseId).orElseThrow(()->new NotFoundException("No course with id " + id + " was found"));

        foundCourse.setCourse_name(course.getCourse_name());
        foundCourse.setCourse_code(course.getCourse_code());
        foundCourse.setFaculty(course.getFaculty());
        foundCourse.setUniversity(course.getUniversity());
        foundCourse.setMaximum_enrollment(course.getMaximum_enrollment());

        return courseRepository.save(foundCourse);
    }

    @DeleteMapping(value = "{id}/courses/{id}")
    public void deleteCourse(@PathVariable Long id,@RequestBody Course course,@PathVariable Long courseId){
        courseRepository.deleteById(courseId);
    }




}
