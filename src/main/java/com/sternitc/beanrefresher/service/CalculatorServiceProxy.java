package com.sternitc.beanrefresher.service;

import com.sternitc.beanrefresher.domain.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class CalculatorServiceProxy implements CalculatorService {

    @Autowired
    private ApplicationContext context;

    @Override
    public int calc(Values values) {
        CalculatorService service = (CalculatorService) context.getBean("calculatorServiceImpl");
        return service.calc(values);
    }

}
