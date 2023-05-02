package com.tutorial.userservice.feignclient;

import com.tutorial.userservice.model.Car;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="car-service",url = "http://localhost:8001/car")
public interface CarFeignClient {

    @PostMapping
    Car saveCar (@RequestBody Car car);

    @GetMapping("/byUser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);
}
