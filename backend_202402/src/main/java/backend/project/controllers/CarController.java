package backend.project.controllers;

import backend.project.dtos.DTOCar;
import backend.project.entities.Brand;
import backend.project.entities.Car;
import backend.project.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping("/cars")
    public ResponseEntity<Car> insertBrand(@RequestBody DTOCar car){
        System.out.println(car);
        Car newCar = carService.addCar(car);
        if (newCar!=null) {
            return new ResponseEntity<>(newCar, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }



}
