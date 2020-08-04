package com.lucasambrosi.water.lack.notifier.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasambrosi.water.lack.notifier.client.dto.request.WaterDistributionRequest;
import com.lucasambrosi.water.lack.notifier.client.dto.response.WaterDistributionResponse;
import com.lucasambrosi.water.lack.notifier.model.WaterDistributionStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class WaterDistributionClient {

    private static final String OBJECT_IDENTIFIER = "{";

    private WebClient webClient;
    private ObjectMapper objectMapper;

    public WaterDistributionClient(final ObjectMapper objectMapper,
                                   @Value("${app.water-distribution.url}") final String url) {
        this.objectMapper = objectMapper;
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Flux<WaterDistributionStatus> get(final WaterDistributionRequest request) {
        return webClient.post()
                .bodyValue(request)
                .exchange()
                .flatMap(this::handleResponse)
                .flatMapIterable(WaterDistributionResponse::getResponse)
                .map(it -> WaterDistributionStatus.withOcurrency(it.getNormalizationShift(), it.getOcurrencyDescription()))
                .defaultIfEmpty(WaterDistributionStatus.withoutOcurrency());
    }

    private Mono<WaterDistributionResponse> handleResponse(final ClientResponse clientResponse) {
        return clientResponse.bodyToMono(String.class)
                .filter(it -> it.startsWith(OBJECT_IDENTIFIER))
                .flatMap(this::parse);
    }

    private Mono<WaterDistributionResponse> parse(final String body) {
        try {
            WaterDistributionResponse response = objectMapper.readValue(body, WaterDistributionResponse.class);
            return Mono.just(response);
        } catch (Exception ex) {
            return Mono.error(ex);
        }
    }
}
