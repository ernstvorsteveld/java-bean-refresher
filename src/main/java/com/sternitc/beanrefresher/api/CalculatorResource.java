package com.sternitc.beanrefresher.api;

import com.sternitc.beanrefresher.service.CalculateMapper;
import com.sternitc.beanrefresher.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class CalculatorResource {

    private final CalculatorService calculatorService;

    private final CalculateMapper mapper;

    @Autowired
    public CalculatorResource(CalculatorService calculatorService, CalculateMapper mapper) {
        this.calculatorService = calculatorService;
        this.mapper = mapper;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "calculate"
    )
    public Mono<ResponseEntity<CalculateResponse>> calculate(
            @RequestBody Mono<CalculateRequest> calculateRequest,
            final ServerWebExchange exchange) {
        return calculateRequest
                .map(r -> mapper.toDomain(r))
                .map(val -> calculatorService.calc(val))
                .map(result -> new ResponseBuilder(exchange).calculated(mapper.toApi(result)));
    }

    private record ResponseBuilder(ServerWebExchange exchange) {

        public ResponseEntity<CalculateResponse> calculated(CalculateResponse response) {
            return ResponseEntity.ok(response);
        }
    }

}
