package com.salesapi.demo.repository;

import com.salesapi.demo.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University,Long> {

    //select * from universities
    List<University> findAll();

    List<University> findByNameStartingWith(String name);

    List<University> findByNameStartingWithAndLocation(String name, String location);

    List<University> findByNameStartingWithAndCapacity(String name, int capacity);

    List<University> findByNameStartingWithAndLatitudeAndLongitude(String name, double latitude, double longitude);

}
