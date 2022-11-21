package pe.edu.upc.ferreshop.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessResponseDTO{
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String description;

    private String brand;

    private String main_img;
}