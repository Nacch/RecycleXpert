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
    private int category;
    private int level;
    private Long userId;  // Para associate el user
}
