package backend.project.repositories;

import backend.project.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Long> {
    @Query(nativeQuery = true, value="SELECT * FROM brands ORDER BY name ASC")
    public List<Brand> listaOrdNombreAsc();

    @Query(value="SELECT b FROM Brand b ORDER BY b.country DESC")
    public List<Brand> listaOrdPaisDesc();

    public List<Brand> findByCountryOrderById(String country);
    public List<Brand> findByCountryOrderByNameDesc(String country);

    public List<Brand> findByCountryAndFundationYearLessThan(String country, Integer fundationYear);

    public List<Brand> findByName(String name);

    public List<Brand> findByCountry(String country);

}
