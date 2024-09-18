package backend.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String description;
    private Date date;
    private String location;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "eventType_id")
    private EventType eventType;

    @OneToMany(mappedBy = "event",fetch = FetchType.EAGER)
    private List<RegisterEvent> registerEvents;  // Relation with RegisterEvent

    @OneToMany(mappedBy = "event",fetch = FetchType.EAGER)
    private List<QuestionEvent> questionEvents;  // Relation with QuestionEvent

    @OneToMany(mappedBy = "event",fetch = FetchType.EAGER)
    private List<Donation> donations;  // Relation with Donation
}
