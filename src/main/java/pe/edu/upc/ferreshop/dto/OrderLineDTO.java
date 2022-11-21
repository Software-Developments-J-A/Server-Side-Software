package pe.edu.upc.ferreshop.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDTO {

    private Long id;
    private ProductDTO product;
    private Double price;
    private Double quantity;
    private Double total;

}
