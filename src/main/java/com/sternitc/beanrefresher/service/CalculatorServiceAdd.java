package com.sternitc.beanrefresher.service;

import com.sternitc.beanrefresher.domain.Values;

public class CalculatorServiceAdd implements CalculatorService {
    @Override
    public int calc(Values values) {
        return values.getVal1() + values.getVal2();
    }
}
