package backend.project;

import backend.project.entities.Brand;
import backend.project.entities.Car;
import backend.project.entities.Model;
import backend.project.repositories.BrandRepository;
import backend.project.repositories.CarRepository;
import backend.project.repositories.ModelRepository;
import backend.project.services.BrandService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@SpringBootApplication
@EnableJpaRepositories("backend.project.repositories")
@EntityScan("backend.project.entities")
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
		BrandRepository brandRepository,
		ModelRepository modelRepository,
		CarRepository carRepository,
		BrandService brandService
	) {
		return args -> {
			Brand brand1 = new Brand(0L,"Toyota","Japon",1920,null);
			Brand brand2 = new Brand(0L,"Nissan","Japon",1955,null);
			Brand brand3 = new Brand(0L,"Mitsubishi","Japon",1911,null);

			Brand brand4 = new Brand(0L,"Honda","China",1993,null);
			Brand brand5 = new Brand(0L,"MG","China",1994,null);
			Brand brand6 = new Brand(0L,"Kia","Japon",1970,null);
			Brand brand7 = new Brand(0L,"Yamaha","Japon",1965,null);

			brand1=brandService.addBrand(brand1);
			System.out.println(brand1);
			System.out.println("----------------------------------");
			brandService.addBrand(brand2);
			brandService.addBrand(brand3);
			brandService.addBrand(brand4);
			brandService.addBrand(brand5);
			brandService.addBrand(brand6);
			brandService.addBrand(brand7);

/*
			brand1=brandRepository.save(brand1);
			System.out.println(brand1);
			System.out.println("----------------------------------");
			brandRepository.save(brand2);
			brandRepository.save(brand3);
			brandRepository.save(brand4);
			brandRepository.save(brand5);
			brandRepository.save(brand6);
			brandRepository.save(brand7);
*/
/*
			List<Brand> brands=brandService.listAll();
			for (Brand brand: brands) {
				System.out.println(brand);
			}
			System.out.println("----------------------------------");
			Brand bien_actualizado = brandRepository.findById(2L).get();
			bien_actualizado.setName("Volvo");
			brandRepository.save(bien_actualizado);

			Brand mal_actualizado = new Brand(1L,"Audi",null,null,null);
			brandRepository.save(mal_actualizado);
			List<Brand> brands2=brandRepository.findAll();
			for (Brand brand: brands2) {
				System.out.println(brand);
			}

			System.out.println("----------------------------------");
			Brand para_borrar = brandRepository.findById(4L).get();
			brandRepository.delete(para_borrar);
			List<Brand> brands3=brandRepository.listaOrdNombreAsc();
			for (Brand brand: brands3) {
				System.out.println(brand);
			}
			System.out.println("----------------------------------");
			List<Brand> brands4=brandRepository.listaOrdPaisDesc();
			for (Brand brand: brands4) {
				System.out.println(brand);
			}
			System.out.println("----------------------------------");
			List<Brand> brands5=brandRepository.findByCountryOrderById("Japon");
			for (Brand brand: brands5) {
				System.out.println(brand);
			}
			System.out.println("----------------------------------");
			List<Brand> brands6=brandRepository.findByCountryOrderByNameDesc("Japon");
			for (Brand brand: brands6) {
				System.out.println(brand);
			}

			System.out.println("----------------------------------");
			List<Brand> brands7=brandRepository.findByCountryAndFundationYearLessThan("Japon",1960);
			for (Brand brand: brands7) {
				System.out.println(brand);
			}

*/
			Brand brandXModel=brandRepository.findById(2L).get();
			Model model1=new Model(0L,"Corolla",brandXModel,null);
			Model model2=new Model(0L,"Yaris",brandXModel ,null);
			Model model3=new Model(0L,"Hilux",brandXModel,null);
			model1=modelRepository.save(model1);
			model2=modelRepository.save(model2);
			model3=modelRepository.save(model3);

			Brand brandXModel2=brandRepository.findById(3L).get();
			Model model4=new Model(0L,"Orion",brandXModel2,null);
			Model model5=new Model(0L,"Lancer",brandXModel2 ,null);
			Model model6=new Model(0L,"Imperius",brandXModel2,null);
			model4=modelRepository.save(model4);
			model5=modelRepository.save(model5);
			model6=modelRepository.save(model6);

			Car car1=new Car(0L,"AT451",17871.0,null,model1,null);
			Car car2=new Car(0L,"AT452",16551.0,null,model1,null);
			Car car3=new Car(0L,"AT453",41232.0,null,model1,null);
			Car car4=new Car(0L,"AT455",42133.0,null,model2,null);
			Car car5=new Car(0L,"AT456",56755.0,null,model2,null);
			Car car6=new Car(0L,"AT457",12341.0,null,model2,null);
			Car car7=new Car(0L,"AT458",32452.0,null,model5,null);
			Car car8=new Car(0L,"AT459",23412.0,null,model5,null);
			Car car9=new Car(0L,"AT4510",13241.0,null,model5,null);
			Car car10=new Car(0L,"AT4511",23421.0,null,model6,null);
			Car car11=new Car(0L,"AT4512",23452.0,null,model6,null);
			Car car12=new Car(0L,"AT4513",23422.0,null,model6,null);
			Car car13=new Car(0L,"AT4514",53423.0,null,model6,null);


			carRepository.save(car1);
			carRepository.save(car2);
			carRepository.save(car3);
			carRepository.save(car4);
			carRepository.save(car5);
			carRepository.save(car6);
			carRepository.save(car7);
			carRepository.save(car8);
			carRepository.save(car9);
			carRepository.save(car10);
			carRepository.save(car11);
			carRepository.save(car12);
			carRepository.save(car13);

/*

			System.out.println("----------------------------------");
			List<Model> models1=modelRepository.findAll();
			for (Model model: models1) {
				model.getBrand().setModels(null);
				System.out.println(model);
			}
			List<Brand> brands8=brandRepository.findAll();
			for (Brand brand: brands8) {
				for (Model model: brand.getModels()) {
					model.setBrand(null);
				}
				System.out.println(brand);
			}
			System.out.println("----------------------------------");

 */
			/*
			Esta parte no funciona pues intenta borrar un registro que es FK de otros registros
			Brand brandABorrar=brandRepository.findById(2L).get();
			brandRepository.delete(brandABorrar);
			List<Brand> brands9=brandRepository.findAll();
			for (Brand brand: brands9) {
				for (Model model: brand.getModels()) {
					model.setBrand(null);
				}
				System.out.println(brand);
			}*/
			/*
			System.out.println("----------------------------------");
			List<Model> models2=modelRepository.findByBrand_Id(3L);
			for (Model model: models2) {
				model.getBrand().setModels(null);
				System.out.println(model);
			}
			*/

		};
	}
}
