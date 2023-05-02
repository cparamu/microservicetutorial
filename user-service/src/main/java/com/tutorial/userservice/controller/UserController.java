package com.tutorial.userservice.controller;

import com.tutorial.userservice.entity.Users;
import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;
import com.tutorial.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> getAll(){
        List<Users> users = userService.getAll();
        if (users.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getByUserId(@PathVariable("id") int id){
        Users user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Users> saveUser (@RequestBody Users user) {
        Users newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("cars/{userId}")
    public ResponseEntity<List<Car>> getCarByUserId(@PathVariable("userId") int userId) {
        Users user = userService.getUserById(userId);
        if ( user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCarbyUserId(userId);
        if ( cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikeByUserId(@PathVariable("userId") int userId) {
        Users user = userService.getUserById(userId);
        if ( user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Bike> bikes = userService.getBikebyUserId(userId);
        if ( bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("saveCar/{userId}")
    public ResponseEntity<Car> saveCarByUser(@PathVariable("userId") int userId, @RequestBody Car car) {
        Car newCar = userService.saveCar(userId, car );
        return ResponseEntity.ok(newCar);
    }

    @PostMapping("saveBike/{userId}")
    public ResponseEntity<Bike> saveBikeBuUser ( @PathVariable("userId") int userId, @RequestBody Bike bike) {
        Bike newBike = userService.saveBike(userId, bike);
        return ResponseEntity.ok(newBike);
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String,Object>> getAllVehicles(@PathVariable("userId") int userId){
        Map<String, Object> result = userService.getAllVehicles(userId);
        return  ResponseEntity.ok(result);
    }

}
