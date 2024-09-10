package backend.project.controllers;

import backend.project.entities.Brand;
import backend.project.entities.Model;
import backend.project.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ModelController {

    @Autowired
    ModelService modelService;

    @GetMapping("/models/brand/{brandId}")
    public ResponseEntity<List<Model>> listBrandsByCountry(@PathVariable("brandId") Long brandId){
        return new ResponseEntity<List<Model>>(modelService.listByBrand_Id(brandId), HttpStatus.OK);
    }


}
