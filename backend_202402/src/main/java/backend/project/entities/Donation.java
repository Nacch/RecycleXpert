package backend.project.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private String donationType;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "Sponsor_id")
    private Sponsor cSponsor;

    @ManyToOne
    @JoinColumn(name = "typeDonation_id")
    private TypeDonation typeDonation;

}
