package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEventDTO {
    private Long id;
    private String eventName;
    private String eventDate;
    private Long volunteerId;  // Relation con Volunteer
}
