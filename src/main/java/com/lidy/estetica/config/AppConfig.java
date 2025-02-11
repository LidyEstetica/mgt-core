package com.lidy.estetica.config;

import com.lidy.estetica.model.mapper.ClienteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ClienteMapper clienteMapper() {
        return ClienteMapper.INSTANCE;
    }
}
