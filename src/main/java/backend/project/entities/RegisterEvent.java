package backend.project.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "registerEvents")
public class RegisterEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean attendance;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;
}
