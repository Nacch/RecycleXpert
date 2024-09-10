package backend.project.services;

import backend.project.dtos.DTOSalesSummary;
import backend.project.entities.Brand;

import java.util.List;

public interface BrandService {

    public Brand addBrand(Brand brand);
    public List<Brand> listAll();

    public List<Brand> listByCountry(String country);
    public List<Brand> listByCountryAndFundationYearLessThan(String country, Integer fundationYear);

    public Brand findById(Long id);
    public Brand updateBrand(Long id, Brand brand);
    public void deleteBrand(Long id);

    public List<DTOSalesSummary> listSalesSummary();

}
