package com.sternitc.beanrefresher.api;

import com.sternitc.beanrefresher.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Bean
    public CalculateMapper calculateMapper() {
        return new CalculateMapperImpl();
    }

    @Bean(value = "calculatorServiceImpl")
    public CalculatorService calculatorServiceImpl() {
        return new CalculatorServiceAdd();
    }

    @Bean(value = "calculatorService")
    public CalculatorService calculatorService() {
        return new CalculatorServiceProxy();
    }
}
