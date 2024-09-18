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
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organizationName;
    private String orgType;
    private String contactInfo;
    private String collaborationArea;

    @OneToMany(mappedBy = "organization",fetch = FetchType.EAGER)
    private List<Event> events;  // Relation with Event

    @OneToMany(mappedBy = "organization",fetch = FetchType.EAGER)
    private List<Consultation> consultations;  // Relation with Consultations
}
