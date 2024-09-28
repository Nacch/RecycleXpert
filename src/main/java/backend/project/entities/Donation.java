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

    private String donorName;
    private String donorEmail;
    private String creditCard; // Puede ser un token si se usa un servicio de procesamiento de pagos
    private double amount;
    private Date donationDate;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
