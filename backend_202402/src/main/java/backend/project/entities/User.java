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
@Table(name = "users")
public class User {
    //ID Autogenerado de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private boolean isActive;
    private String field;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Volunteer> volunteers;  // Relation n Volunteer
}
