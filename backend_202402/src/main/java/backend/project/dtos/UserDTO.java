package backend.project.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private boolean isActive;
    private String field;
    private Long authorityId;  // Se usa para associate la authority
}
