package backend.project.controllers;

import backend.project.dtos.DTOSalesSummary;
import backend.project.entities.Brand;
import backend.project.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
// "http://localhost:8080/api"
public class BrandController {

    @Autowired
    BrandService brandService;

    //HTTP: GET, POST, PUT, DELETE
    @GetMapping("/brands")
    // "http://localhost:8080/api/brands"
    public ResponseEntity<List<Brand>> listAllBrands(){
        return new ResponseEntity<List<Brand>>(brandService.listAll(), HttpStatus.OK);
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<HttpStatus> deleteBrand(@PathVariable("id") Long id){
        brandService.deleteBrand(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> buscarBrand(@PathVariable("id") Long id) {
        Brand brandFound = brandService.findById(id);
        if (brandFound==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(brandFound, HttpStatus.OK);

    }



    @GetMapping("/brands/country/{name}")
    public ResponseEntity<List<Brand>> listBrandsByCountry(@PathVariable("name") String name){
        return new ResponseEntity<List<Brand>>(brandService.listByCountry(name), HttpStatus.OK);
    }


    @GetMapping("/brands/country/{name}/year/{year}")
    public ResponseEntity<List<Brand>> listBrandsByCountryAndFundationYearLessThan(@PathVariable("name") String name,
                                                                                   @PathVariable("year") Integer year ){
        return new ResponseEntity<List<Brand>>(brandService.listByCountryAndFundationYearLessThan(name,year), HttpStatus.OK);
    }

    @PostMapping("/brands")
    public ResponseEntity<Brand> insertBrand(@RequestBody Brand brand){
        Brand newBrand = brandService.addBrand(brand);
        if (newBrand!=null) {
            return new ResponseEntity<>(newBrand, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable("id") Long id, @RequestBody Brand brand){
        Brand updateBrand = brandService.updateBrand(id, brand);
        if (updateBrand!=null) {
            return new ResponseEntity<>(updateBrand, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @PutMapping("/brands")
    public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand){
        Brand updateBrand = brandService.updateBrand(brand.getId(), brand);
        if (updateBrand!=null) {
            return new ResponseEntity<>(updateBrand, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/brands/sales_summary")
    public ResponseEntity<List<DTOSalesSummary>> listSalesSummary(){
        return new ResponseEntity<List<DTOSalesSummary>>(brandService.listSalesSummary(), HttpStatus.OK);
    }

}
