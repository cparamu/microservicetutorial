package com.tutorial.carmicroservice.controller;

import com.tutorial.carmicroservice.entity.Car;
import com.tutorial.carmicroservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        List<Car> cars = carService.getAll();
        if (cars.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getByCarId(@PathVariable("id") int id){
        Car car = carService.getCarById(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> saveUser (@RequestBody Car car) {
        Car newCar = carService.saveCar(car);
        return ResponseEntity.ok(newCar);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Car>> getByUserId( @PathVariable("userId") int userId){
        List<Car> cars = carService.getCarByUserId(userId);
        if (cars.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
}
