package com.example.ProductMicro;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class OrderServiceClient {
    private final WebClient webClient;

    public OrderServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://order-service-env.abc123.eu-north-1.elasticbeanstalk.com").build();
    }

    public Mono<Integer> getOrderCountByProductId(Long productId) {
        return webClient.get()
                .uri("/orders/count/{productId}", productId)
                .retrieve()
                .bodyToMono(Integer.class);
    }
}

