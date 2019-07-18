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
        University jkuat = new University("JKUAT","juja", 3000, 0.314, 34.5678);

        University strathmore = new University("Strathmore", "OleSangale", 4000, 0, 0);

        universityRepository.saveAll(Arrays.asList(strathmore,jkuat));

        University uon = new University("UON","CBD", 2500, 0.2678, 35.678);
        universityRepository.save(uon);

        Course course1 = new Course("API","FIT","BBT009", 7,strathmore);
        courseRepository.save(course1);

        Course course2 = new Course("Automata", "BIT", "008UI",7,jkuat);
        courseRepository.save(course2);
    }





}
