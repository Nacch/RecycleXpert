package backend.project.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerDTO {
    private Long id;
    private String volunteerName;
    private String email;
    private String address;
    private int points;
    private String category;
    private int level;
    private Long rangoId;  // Foreign key reference to Rango
    private Long userId;   // Foreign key reference to User
}
