package com.sternitc.beanrefresher.service;

import com.sternitc.beanrefresher.domain.Values;

public class CalculatorServiceMult implements CalculatorService {
    @Override
    public int calc(Values values) {
        return values.getVal1() * values.getVal2();
    }
}
