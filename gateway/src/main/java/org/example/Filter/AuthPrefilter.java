package org.example.Filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class AuthPrefilter implements GatewayFilter {

    @Override
    public Mono <Void> filter(
            final ServerWebExchange exchange,
            final GatewayFilterChain chain
    ){
        return this.onError(exchange, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Mono<Void> onError(
            final ServerWebExchange exchange,
            final HttpStatusCode statusCode
    ) {
        var response = exchange.getResponse();
        response.setStatusCode(statusCode);
        return response.setComplete();
    }
}
