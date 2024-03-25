package org.example.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@RequiredArgsConstructor

public class GatewayConfig {
    public static void main(String[] args) {
        SpringApplication.run(GatewayConfig.class, args);
    }

    @Bean
    public RouteLocator myRoutes(
            final RouteLocatorBuilder builder
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
                                .filters(f -> f.rewritePath(
                                        "/api/user/me/.*",
                                        "/user/NotAllowed"))
                                .uri("lb://user-service")
                )
                .route(
                        "ticket-service",
                        r -> r.path("/api/ticket/user/me/**")
                                .filters(f -> f.rewritePath(
                                        "/api/ticket/user/me/.*",
                                        "/user/NotAllowed"))
                                .uri("lb://ticket-service")
                )
                .route(
                        "event-service-no-auth",
                        r -> r.path("/api/event/**")
                                .uri("lb://event-service")
                )
                .route(
                        "categories-service-no-auth",
                        r -> r.path("/api/categories/**")
                                .uri("lb://event-service")
                )
                .route(
                        "sous-categories-service-no-auth",
                        r -> r.path("/api/categories/sous-categories/**")
                                .uri("lb://event-service")
                )
                .route(
                        "order-service-auth",
                        r -> r.path("/api/order/user/me/**")
                                .filters(f -> f.rewritePath(
                                        "/api/order/user/me/.*",
                                        "/user/NotAllowed"))
                                .uri("lb://order-service")
                )
                .route(
                        "payment-service-auth",
                        r -> r.path("/api/payment/user/me/**")
                                .filters(f -> f.rewritePath(
                                        "/api/payment/user/me/.*",
                                        "/user/NotAllowed"))
                                .uri("lb://payment-service")
                )
                .build();

    }
}
