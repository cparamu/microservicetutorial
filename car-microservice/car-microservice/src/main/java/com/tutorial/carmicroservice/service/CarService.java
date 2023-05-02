package com.tutorial.carmicroservice.service;

import com.tutorial.carmicroservice.entity.Car;
import com.tutorial.carmicroservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getCarById(int id){
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(Car car){
        Car newCar = carRepository.save(car);
        return newCar;
    }

    public List<Car> getCarByUserId(int userId) {
        return carRepository.findByUserId(userId);
    }

}
