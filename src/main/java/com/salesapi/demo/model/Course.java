package com.salesapi.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "course_name")
    private String course_name;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "course_code")
    private String course_code;

    @Column(name = "maximum_enrollment")
    private int maximum_enrollment;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToMany(mappedBy = "courses")
    private Set<Human> students = new HashSet<>();

    public Course(String course_name, String faculty, String course_code, int max_enrollment,University university){
        this.course_code = course_code;
        this.course_name = course_name;
        this.maximum_enrollment = max_enrollment;
        this.faculty = faculty;
        this.university = university;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    private Course(){}

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public int getMaximum_enrollment() {
        return maximum_enrollment;
    }

    public void setMaximum_enrollment(int maximum_enrollment) {
        this.maximum_enrollment = maximum_enrollment;
    }

    public Set<Human> getStudents() {
        return students;
    }

    public void setStudents(Set<Human> students) {
        this.students = students;
    }
}
