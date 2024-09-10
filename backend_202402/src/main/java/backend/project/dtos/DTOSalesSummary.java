package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOSalesSummary {
    private Long brandId;
    private String brandName;
    private Integer countSales;
    private Double averagePrice;
}
