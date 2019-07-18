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

    @GetMapping(value = "{id}/courses/{courseId}")
    public Course findOneById(@PathVariable Long id, @PathVariable Long courseId){
         Course course =  courseRepository.findById(courseId).orElseThrow(()->new NotFoundException("No course with id " + courseId + " was found"));

         return course;
    }

    @PostMapping(value = "{id}/courses")
    public Course createUniversityCourse(@PathVariable Long id,@RequestBody Course courses){
        return courseRepository.save(courses);
    }


    @PatchMapping(value = "{id}/courses/{courseId}")
    public Course updateCourse(@PathVariable Long id,@RequestBody Course course,@PathVariable Long courseId){
        Course foundCourse =  courseRepository.findById(courseId).orElseThrow(()->new NotFoundException("No course with id " + id + " was found"));

        foundCourse.setCourse_name(course.getCourse_name());
        foundCourse.setCourse_code(course.getCourse_code());
        foundCourse.setFaculty(course.getFaculty());
        foundCourse.setUniversity(course.getUniversity());
        foundCourse.setMaximum_enrollment(course.getMaximum_enrollment());

        return courseRepository.save(foundCourse);
    }

    @DeleteMapping(value = "{id}/courses/{courseId}")
    public void deleteCourse(@PathVariable Long id,@RequestBody Course course,@PathVariable Long courseId){
        courseRepository.deleteById(courseId);
    }

    @GetMapping(value = "search")
    public List<University> search(@RequestParam(required = false) String name,@RequestParam(required = false) String location,@RequestParam(required = false) int capacity,@RequestParam(required = false, defaultValue = "0") double latitude, @RequestParam(required = false, defaultValue = "0") double longitude){
        if(location != null) {
            return universityRepository.findByNameStartingWithAndLocation(name, location);
        }else if(capacity >= 2000 && capacity <= 5000) {
//            return universityRepository.findByNameStartingWithAndCapacity(name, capacity);
            return universityRepository.findByCapacity(capacity);
        }else if(capacity < 3000) {
            return universityRepository.findByNameStartingWithAndCapacity(name, capacity);
        }else if(capacity != 4000) {
            return universityRepository.findByNameStartingWithAndCapacity(name, capacity);
        }else if(latitude != 0 && longitude != 0) {
            return universityRepository.findByNameStartingWithAndLatitudeAndLongitude(name, latitude, longitude);
        }else
            return universityRepository.findByNameStartingWith(name);
    }

//    @GetMapping(value = "multisearch")
//    public List<University> search(@RequestParam String name, @RequestParam(required = false) String location){
//        if(location != null)
//            return universityRepository.findByNameStartingWithAndLocation(name,location);
//        else
//            return universityRepository.findByNameStartingWith(name);
//    }

}
