package com.sternitc.beanrefresher.actuator;

import com.sternitc.beanrefresher.service.CalculatorService;
import com.sternitc.beanrefresher.service.CalculatorServiceAdd;
import com.sternitc.beanrefresher.service.CalculatorServiceMult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.ApplicationContext;

@Endpoint(id = "refresh")
public class BeanRefresher {

    @Autowired
    ApplicationContext applicationContext;

    @WriteOperation
    public String setOperation(String type) {
        DefaultSingletonBeanRegistry registry =
                (DefaultSingletonBeanRegistry) applicationContext.getAutowireCapableBeanFactory();
        CalculatorService service;
        if (type.equalsIgnoreCase("ADD")) {
            service = new CalculatorServiceAdd();
        } else {
            service = new CalculatorServiceMult();
        }
        registry.destroySingleton("calculatorServiceImpl");
        registry.registerSingleton("calculatorServiceImpl", service);

        return service.getClass().getName();
    }
}
