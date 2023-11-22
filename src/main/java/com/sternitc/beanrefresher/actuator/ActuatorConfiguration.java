package com.sternitc.beanrefresher.actuator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfiguration {

    @Bean
    public BeanRefresher beanRefresher() {
        return new BeanRefresher();
    }
}
