package pe.edu.upc.ferreshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConifg {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
