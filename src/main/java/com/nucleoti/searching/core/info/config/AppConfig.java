package com.nucleoti.searching.core.info.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CheckoutdataProperties properties() {
        return new CheckoutdataProperties();
    }


}
