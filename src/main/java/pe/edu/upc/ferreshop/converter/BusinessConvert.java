package pe.edu.upc.ferreshop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pe.edu.upc.ferreshop.dto.BusinessResponseDTO;
import pe.edu.upc.ferreshop.entities.Business;

@Component
public class BusinessConvert {
    private final ModelMapper modelMapper;

    public BusinessConvert(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BusinessResponseDTO convertEntityToDto(Business business) {
        return modelMapper.map(business,BusinessResponseDTO.class);
    }
}
