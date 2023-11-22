package com.sternitc.beanrefresher.api;

import com.sternitc.beanrefresher.actuator.ActuatorConfiguration;
import com.sternitc.beanrefresher.actuator.BeanRefresher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CalculatorResource.class)
@Import({ApiConfiguration.class, ActuatorConfiguration.class})
@AutoConfigureMockMvc
class CalculatorResourceTest {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private BeanRefresher beanRefresher;

    @Test
    public void should_execute_addition() {
        CalculateRequest request = CalculateRequest.builder().val1(1).val2(2).build();
        webClient.post()
                .uri("http://localhost:8080/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CalculateResponse.class)
                .value(CalculateResponse::getResult, is(3));

        beanRefresher.setOperation("MULT");

        webClient.post()
                .uri("http://localhost:8080/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CalculateResponse.class)
                .value(CalculateResponse::getResult, is(2));
    }

    private static class Operation {
        private final String type;

        private Operation(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

}