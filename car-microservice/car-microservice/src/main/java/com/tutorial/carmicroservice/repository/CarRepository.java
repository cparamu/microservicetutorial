package com.tutorial.carmicroservice.repository;

import com.tutorial.carmicroservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository  extends JpaRepository<Car,Integer> {

    List<Car> findByUserId(int id);
}
