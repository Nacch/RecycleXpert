package backend.project.dtos;

import backend.project.entities.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationDTO {
    private Long id;
    private String donorName;
    private String donorEmail;
    private String creditCard; // Puede ser un token si se usa un servicio de procesamiento de pagos
    private double amount;
    private Date donationDate;
    private Event event;
}
