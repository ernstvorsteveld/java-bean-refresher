package com.sternitc.beanrefresher.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CalculateRequest {

    private int val1;
    private int val2;

}
