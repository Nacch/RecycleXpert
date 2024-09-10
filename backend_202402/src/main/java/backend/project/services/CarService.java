package backend.project.services;

import backend.project.dtos.DTOCar;
import backend.project.entities.Brand;
import backend.project.entities.Car;

public interface CarService {
    public Car addCar(DTOCar dtoCar);

}
