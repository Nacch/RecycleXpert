package backend.project.serviceimpl;

import backend.project.dtos.DTOSalesSummary;
import backend.project.entities.Brand;
import backend.project.entities.Car;
import backend.project.entities.Model;
import backend.project.exceptions.InvalidActionException;
import backend.project.exceptions.KeyRepeatedDataException;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.BrandRepository;
import backend.project.services.BrandService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    public boolean isNameDuplicated(String name){
        List<Brand> listNombreDuplicados=brandRepository.findByName(name);
        return (listNombreDuplicados.size()>0);
    }

    @Override
    public Brand addBrand(Brand brand) {

        //Voy a confirmar que no se repita el nombre de la marca
        if (isNameDuplicated(brand.getName())) {
            throw new KeyRepeatedDataException("Brand name: "+ brand.getName() +" can not be duplicated");
            //return null;
        }
        if ((brand.getCountry()==null || brand.getCountry().isBlank())) {
            throw new KeyRepeatedDataException("Brand country can not be blank");
        }

            //Agregar la nueva marca
        return brandRepository.save(brand);


    }

    @Override
    public List<Brand> listAll() {
        List<Brand> brandList = brandRepository.findAll();
        for (Brand b: brandList){
            for (Model m: b.getModels()) {
                m.setBrand(null);
            }
        }
        return brandList;
    }

    @Override
    public List<Brand> listByCountry(String country) {
        List<Brand> brandList = brandRepository.findByCountry(country);
        for (Brand b: brandList){
            for (Model m: b.getModels()) {
                m.setBrand(null);
            }
        }
        return brandList;
    }

    @Override
    public List<Brand> listByCountryAndFundationYearLessThan(String country, Integer fundationYear) {
        List<Brand> brandList = brandRepository.findByCountryAndFundationYearLessThan(country, fundationYear);
        /*
        for (Brand b: brandList){
            for (Model m: b.getModels()) {
                m.setBrand(null);
            }
        }
        */

        return brandList;
    }



    @Override
    public Brand findById(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand==null) {
            throw new ResourceNotFoundException("Brand id: "+ id +" can not be found");
        }
        return brand;
    }

    @Override
    public Brand updateBrand(Long id, Brand brand) {
        Brand brandFound = findById(id);
        if (brandFound!=null) {
            if (brand.getName()!=null) {
                if (!isNameDuplicated(brand.getName())) {
                    brandFound.setName(brand.getName());
                } else {
                    throw new KeyRepeatedDataException("Brand name: "+ brand.getName() +" can not be duplicated");
                }
            }
            if (brand.getCountry()!=null) {
                brandFound.setCountry(brand.getCountry());
            }
            if (brand.getFundationYear()!=null) {
                brandFound.setFundationYear(brand.getFundationYear());
            }
            return brandRepository.save(brandFound);
        }
        return null;
    }

    @Override
    public void deleteBrand(Long id) {
        Brand brand = findById(id);
        if (brand!=null) {
            if (brand.getModels().isEmpty()) {
                brandRepository.delete(brand);
            } else {
                throw new InvalidActionException("Brand with id: "+ id +" can not be deleted because it has FK dependencies");
            }
        }
    }

    @Override
    public List<DTOSalesSummary> listSalesSummary() {
        List<DTOSalesSummary> salesSummaryList = new ArrayList<>();

        List<Brand> brands=brandRepository.findAll();
        for (Brand brand: brands) {

            Long brandId = brand.getId();
            String brandName = brand.getName();

            Integer countSales = 0;
            Double averagePrice=0.0;
            Double sumPrices=0.0;

            for (Model model: brand.getModels()) {
                countSales += model.getCars().size();
                for (Car car: model.getCars()) {
                    sumPrices += car.getSalePrice();
                }
            }

            /*
            countSales = brand.getModels().stream().map(m->m.getCars().size()).reduce(0,Integer::sum);
            sumPrices =  brand.getModels().stream().map(m->m.getCars().stream().map(c->c.getSalePrice()).reduce(0.0,Double::sum)).reduce(0.0,Double::sum);
            */
            if (countSales>0) {
                averagePrice = sumPrices/countSales;
            }


            DTOSalesSummary dtoSalesSummary=new DTOSalesSummary(brandId, brandName, countSales, averagePrice );

            salesSummaryList.add(dtoSalesSummary);
        }

        return salesSummaryList;
    }
}
