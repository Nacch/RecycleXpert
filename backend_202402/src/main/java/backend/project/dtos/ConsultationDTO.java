package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {
    private Long id;
    private String consultationDetails;
    private Long volunteerId;  // Relation con Volunteer
}
