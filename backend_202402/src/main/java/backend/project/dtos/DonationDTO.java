package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationDTO {
    private Long id;
    private double amount;
    private String donationDate;
    private Long volunteerId;  // Relation con Volunteer
    private Long typeDonationId;  // Relation con TypeDonation
}
