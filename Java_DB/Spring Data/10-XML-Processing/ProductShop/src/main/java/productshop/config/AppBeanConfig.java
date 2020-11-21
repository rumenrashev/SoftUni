package productshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productshop.util.XmlParser;
import productshop.util.XmlParserImpl;

@Configuration
public class AppBeanConfig {

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
