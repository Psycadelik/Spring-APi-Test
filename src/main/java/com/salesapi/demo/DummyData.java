package com.salesapi.demo;

import com.salesapi.demo.model.Course;
import com.salesapi.demo.model.Human;
import com.salesapi.demo.model.University;
import com.salesapi.demo.repository.CourseRepository;
import com.salesapi.demo.repository.HumanRepository;
import com.salesapi.demo.repository.UniversityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DummyData implements CommandLineRunner {

    private final UniversityRepository universityRepository;
    private final CourseRepository courseRepository;
    private final HumanRepository humanRepository;

    public DummyData(UniversityRepository universityRepository, CourseRepository courseRepository, HumanRepository humanRepository) {
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
        this.humanRepository = humanRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        University jkuat = new University("JKUAT","juja", 3000, 0.314, 34.5678);

        //set chancellor
        Human chancellor = humanRepository.save(new Human("Omollo Owuor"));
        jkuat.setChancellor(chancellor);

        University strathmore = new University("Strathmore", "OleSangale", 4000, 0, 0);

        //set chancellor
        Human chancellor2 = humanRepository.save(new Human("Prof. Francis"));
        strathmore.setChancellor(chancellor2);
        universityRepository.saveAll(Arrays.asList(strathmore,jkuat));

        University uon = new University("UON","CBD", 2500, 0.2678, 35.678);
        universityRepository.save(uon);

        Course course1 = new Course("API","FIT","BBT009", 7,strathmore);
        courseRepository.save(course1);

        Course course2 = new Course("Automata", "BIT", "008UI",7,jkuat);
        courseRepository.save(course2);

        Human student = humanRepository.save(new Human("Adrian"));
        student.addCourse(course1);
        student.addCourse(course2);
        humanRepository.save(student);

        Human student2 = humanRepository.save(new Human("Joy"));
        student2.addCourse(course1);
        humanRepository.save(student2);
    }





}
