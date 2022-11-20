package pe.edu.upc.ferreshop.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProcDTO {

    private Integer quantity;
    private LocalDateTime orderdate;

}
