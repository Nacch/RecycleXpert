package backend.project.serviceimpl;

import backend.project.dtos.DTOCar;
import backend.project.entities.Car;
import backend.project.entities.Model;
import backend.project.entities.User;
import backend.project.repositories.CarRepository;
import backend.project.repositories.ModelRepository;
import backend.project.repositories.UserRepository;
import backend.project.services.CarService;
import backend.project.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;

    @Autowired
    ModelRepository modelRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Car addCar(DTOCar dtoCar) {

        Model model = modelRepository.findById(dtoCar.getModel_id()).orElse(null);
        User user = userRepository.findById(dtoCar.getUser_id()).orElse(null);

        //Opcion 1, copiar cada atributo
        Car car = new Car(0L, dtoCar.getLicense(), dtoCar.getSalePrice(), dtoCar.getFabricationDate(),model,user);
        car.setUser(user);
        car.setModel(model);


        return carRepository.save(car);

    }
}
