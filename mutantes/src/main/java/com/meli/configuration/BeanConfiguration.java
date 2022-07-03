package com.meli.configuration;

import com.meli.Application;
import com.meli.domain.service.SecuenciaService;
import com.meli.infrastructure.adapter.SecuenciaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
public class BeanConfiguration {

    @Bean
    SecuenciaService secuenciaService(SecuenciaRepository repository) {
        return new SecuenciaService(repository, repository);
    }

}
