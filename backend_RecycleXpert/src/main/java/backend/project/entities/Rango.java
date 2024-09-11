package backend.project.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rango")
public class Rango {

    //ID Autogenerado de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rango_name")
    private String ranName;

    private String description;
    private Integer rewards;
    private Integer maxPoint;
    private Integer minPoints;

    @OneToMany(mappedBy = "rango")
    private List<Volunteer> volunteers;
}
