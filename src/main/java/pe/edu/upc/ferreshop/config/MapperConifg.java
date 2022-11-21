package pe.edu.upc.ferreshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.upc.ferreshop.converter.OrderConvert;
import pe.edu.upc.ferreshop.converter.ProductConvert;

@Configuration
public class MapperConifg {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public OrderConvert getOrderConverter() {
        return new OrderConvert(getProductConverter());
    }
    @Bean
    public ProductConvert getProductConverter() {
        return new ProductConvert();
    }
}
