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
@Table(name = "eventTypes")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventTypeName;
    private String description;
    private int rewards;

    @OneToMany(mappedBy = "eventType",fetch = FetchType.EAGER)
    private List<Event> events;  // Relation with Event
}
