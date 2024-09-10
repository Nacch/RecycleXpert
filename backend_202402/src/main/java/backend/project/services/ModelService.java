package backend.project.services;

import backend.project.entities.Brand;
import backend.project.entities.Model;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ModelService {

    public List<Model> listByBrand_Id(Long brandId);

}
