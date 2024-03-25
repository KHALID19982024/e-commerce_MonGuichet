package org.example.Config;

import lombok.RequiredArgsConstructor;
import org.example.Filter.AuthPrefilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    @Bean
    public RouteLocator myRoutes(
            final RouteLocatorBuilder builder,
            final AuthPrefilter authFilter
    ) {
        return builder.routes()
                .route(
                        "auth-service",
                        r -> r.path("/api/auth/**")
                                .uri("lb://auth-service")
                )
                .route(
                        "user-service",
                        r -> r.path("/api/user/me/**")
                                .filters(f -> f.filter(authFilter))
                                .uri("lb://user-service")
                )
                .route(
                        "ticket-service",
                        r -> r.path("/api/ticket/user/me/**")
                                .filters(f -> f.filter(authFilter))
                                .uri("lb://ticket-service")
                )
                .route(
                        "event-service-no-auth",
                        r -> r.path("/api/event/**")
                                .uri("lb://event-service")
                )
                .route(
                        "categories-service-no-auth",
                        r -> r.path("/api/categories/event/**")
                                .uri("lb://event-service")
                )
                .route(
                        "sous-categories-service-no-auth",
                        r -> r.path("/api/categories/sous-categories/event/**")
                                .uri("lb://event-service")
                )
                .route(
                        "order-service-auth",
                        r -> r.path("/api/order/user/me/**")
                                .filters(f -> f.filter(authFilter))
                                .uri("lb://order-service")
                )
                .route(
                        "payment-service-auth",
                        r -> r.path("/api/payment/user/me/**")
                                .filters(f -> f.filter(authFilter))
                                .uri("lb://payment-service")
                )
                .build();

    }
}
