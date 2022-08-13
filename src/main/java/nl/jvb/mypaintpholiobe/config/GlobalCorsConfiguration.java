package nl.jvb.mypaintpholiobe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfiguration {

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return addCorsMappings(registry) -> {
//            registry.addMapping("/**")
//                    .allowedOrigins("*")
//                    .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
//        };
//    }
}
