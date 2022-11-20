package pe.edu.upc.ferreshop.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
}