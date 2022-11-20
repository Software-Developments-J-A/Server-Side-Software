package pe.edu.upc.ferreshop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pe.edu.upc.ferreshop.dto.LoginResponseDTO;
import pe.edu.upc.ferreshop.entities.User;

@Component
public class UserConverter {
    private final ModelMapper modelMapper;

    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LoginResponseDTO convertEntityToDto(User user) {
        return modelMapper.map(user, LoginResponseDTO.class);
    }
}
