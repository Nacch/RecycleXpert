package backend.project.dtos;

import backend.project.entities.Model;
import backend.project.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOCar {
    private Long id;
    private String license;
    private Double salePrice;
    private LocalDate fabricationDate;
    private Long model_id;
    private Long user_id;
}
