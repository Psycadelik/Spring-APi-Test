package com.salesapi.demo.repository;

import com.salesapi.demo.model.Human;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanRepository extends JpaRepository<Human, Long> {

}
