package com.sternitc.beanrefresher.service;

import com.sternitc.beanrefresher.api.CalculateRequest;
import com.sternitc.beanrefresher.api.CalculateResponse;
import com.sternitc.beanrefresher.domain.Values;

public interface CalculateMapper {

    Values toDomain(CalculateRequest request);

    CalculateResponse toApi(Integer result);
}
