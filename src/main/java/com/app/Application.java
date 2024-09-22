package com.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@SpringBootApplication
public class Application {

	@Value("${frontend.url}")
	private String fronendUrl;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean  // equivalent to <bean id ..../> in xml file
    public ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
    @Bean
    public WebMvcConfigurer corsConfigure() {
    	return new WebMvcConfigurer() {
    		@Override
    		public void addCorsMappings(CorsRegistry registry) {
    			registry.addMapping("/**")
    			.allowedOrigins(fronendUrl)
    			.allowedHeaders("*")
    			.allowedMethods("GET","POST","PUT","DELETE")
    			.allowCredentials(true);
    		}
    	};
    }

}
