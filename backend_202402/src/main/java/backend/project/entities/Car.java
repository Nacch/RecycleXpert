package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String license;

    private Double salePrice;

    private LocalDate fabricationDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="model_id")
    private Model model;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
