package org.example.Config;


import lombok.AllArgsConstructor;
import org.example.filter.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class GatewayConfig {
    private final JwtAuthenticationFilter filter;
    @Bean
    public RouteLocator myRoutes(
            final RouteLocatorBuilder builder
    ) {
        return builder.routes()
                .route("user2-microservice",r -> r.path("/api/v1/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user2-microservice")
                )
                .route("security-microservice", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://security-microservice"))

//                .route(r -> r.path("/api/users/**")
//                        .filters(f -> f.rewritePath(
//                                "/api/users",
//                                "/users/NotAllowed"))
//                        .uri("lb://USER2-SERVICE")
                //)
                .route("order-microservice",r -> r.path("/api/order/**")
                        .filters(f -> f.rewritePath(
                                "/api/order",
                                "/order/NotAllowed"))
                        .uri("lb://order-microservice")
                )
                .route("order2-microservice",r -> r.path("/api/order2/**")
                        .filters(f -> f.rewritePath(
                                "/api/order2",
                                "/order/NotAllowed"))
                        .uri("lb://order2-microservice")
                )
                .route("payment-microservice",r -> r.path("/api/payments/**")
                        .filters(f -> f.rewritePath(
                                "/api/payments",
                                "/payments/NotAllowed"))
                        .uri("lb://payment-microservice")
                )
                .route("review-microservice",r -> r.path("/api/v1/review/**")
                        .filters(f -> f.rewritePath(
                                "/api/v1/review",
                                "/api/v1/review/NotAllowed"))
                        .uri("lb://review-microservice")
                )
                .route("catalog-microservice",r -> r.path("/api/v1/category/**")
                        .filters(f -> f.rewritePath(
                                "/api/v1/category",
                                "/api/v1/review/NotAllowed"))
                        .uri("lb://catalog-microservice")
                )


//                .route(
//                        "ticket-service",
//                        r -> r.path("/api/ticket/**")
//                                .filters(f -> f.rewritePath(
//                                        "/api/ticket/*.",
//                                        "/user/NotAllowed"))
//                                .uri("lb://TICKET-SERVICE")
//                )
//                .route(
//                        "payment-service",
//                        r -> r.path("/api/payments/**")
//                                .filters(f -> f.rewritePath(
//                                        "/api/payments/*.",
//                                        "/user/NotAllowed"))
//                                .uri("lb://PAYMENT-SERVICE")
//                )
//                .route(
//                        "order-service",
//                        r -> r.path("/api/order/**")
//                                .filters(f -> f.rewritePath(
//                                        "/api/order/*.",
//                                        "/user/NotAllowed"))
//                                .uri("lb://ORDER-SERVICE")
//                )
//                .route(
//                        "product-categories",
//                        r -> r.path("/api/categories/**")
//                                .filters(f -> f.rewritePath(
//                                        "/api/categories/*.",
//                                        "/user/NotAllowed"))
//                                .uri("lb://PRODUCT-SERVICE")
//                )
//                .route(
//                        "product-sous-categories",
//                        r -> r.path("/api/sous-categories/**")
//                                .filters(f -> f.rewritePath(
//                                        "/api/sous-categories/*.",
//                                        "/user/NotAllowed"))
//                                .uri("lb://PRODUCT-SERVICE")
//                )
//                .route(
//                        "product-events",
//                        r -> r.path("/api/events/**")
//                                .filters(f -> f.rewritePath(
//                                        "/api/events/*.",
//                                        "/user/NotAllowed"))
//                                .uri("lb://PRODUCT-SERVICE")
//                )
                .build();

    }
}
