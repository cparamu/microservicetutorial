package com.tutorial.userservice.service;

import com.tutorial.userservice.entity.Users;
import com.tutorial.userservice.feignclient.BikeFeignClient;
import com.tutorial.userservice.feignclient.CarFeignClient;
import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;
import com.tutorial.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<Users> getAll() {
        return userRepository.findAll();
    }

    public Users getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public Users saveUser(Users user){
        Users newUser = userRepository.save(user);
        return newUser;
    }

    public List<Car> getCarbyUserId(int userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8001/car/byUser/"+userId,List.class);
        return cars;
    }


    public List<Bike> getBikebyUserId(int userId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byUser/"+userId,List.class);
        return bikes;
    }

    public Car saveCar(int userId,  Car car ) {
        car.setUserId(userId);
        Car newCar = carFeignClient.saveCar(car);
        return newCar;

    }

    public Bike saveBike ( int userId, Bike bike ) {
        bike.setUserId(userId);
        Bike newBike = bikeFeignClient.saveBike( bike );
        return newBike;
    }

    public Map<String,Object> getAllVehicles(int userId){
        Map<String,Object> results = new HashMap<String, Object>() ;
        Users users = userRepository.findById(userId).orElse(null);
        if (users == null) {
            results.put("Mensaje","No existe el usuario");
            return results;
        }
        results.put("User",users);
        List<Car> cars = carFeignClient.getCars(userId);
        if (cars.isEmpty()){
            results.put("Message", "Este usuario no tiene carros");
        } else {
            results.put("Cars: ", cars);
        }
        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if (bikes == null) {
            results.put("Menssage: ", "Este usuario no tiene bicicletas");
        } else {
            results.put("Bikes: ",bikes);
        }
        return results;
    }
}
