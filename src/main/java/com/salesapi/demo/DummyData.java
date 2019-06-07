package com.salesapi.demo;

import com.salesapi.demo.model.Course;
import com.salesapi.demo.model.University;
import com.salesapi.demo.repository.CourseRepository;
import com.salesapi.demo.repository.UniversityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DummyData implements CommandLineRunner {

    private final UniversityRepository universityRepository;
    private final CourseRepository courseRepository;

    public DummyData(UniversityRepository universityRepository, CourseRepository courseRepository) {
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        University jkuat = new University("JKUAT","juja");

        University strathmore = new University("Strathmore", "OleSangale");

        universityRepository.saveAll(Arrays.asList(strathmore,jkuat));

        University uon = new University("UON","CBD");
        universityRepository.save(uon);

        Course course1 = new Course("API","FIT","BBT009", strathmore);
        courseRepository.save(course1);

        Course course2 = new Course("Automata", "BIT", "008UI",jkuat);
        courseRepository.save(course2);
    }





}
