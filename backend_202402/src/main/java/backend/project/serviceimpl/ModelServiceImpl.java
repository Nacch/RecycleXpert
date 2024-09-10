package backend.project.serviceimpl;

import backend.project.entities.Model;
import backend.project.repositories.ModelRepository;
import backend.project.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelRepository modelRepository;


    @Override
    public List<Model> listByBrand_Id(Long brandId) {
        return modelRepository.findByBrand_Id(brandId);
    }
}
