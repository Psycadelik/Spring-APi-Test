package com.salesapi.demo.repository;

import com.salesapi.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByUniversityId(Long id);

//    List<Course> findByCourseId(Long id, int enrollment);

}
