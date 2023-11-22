package com.sternitc.beanrefresher.service;

import com.sternitc.beanrefresher.api.CalculateRequest;
import com.sternitc.beanrefresher.api.CalculateResponse;
import com.sternitc.beanrefresher.domain.Values;

public class CalculateMapperImpl implements CalculateMapper {
    @Override
    public Values toDomain(CalculateRequest request) {
        return Values.builder().val1(request.getVal1()).val2(request.getVal2()).build();
    }

    @Override
    public CalculateResponse toApi(Integer result) {
        return CalculateResponse.builder().result(result).build();
    }
}
