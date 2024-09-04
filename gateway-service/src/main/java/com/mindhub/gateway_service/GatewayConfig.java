package com.mindhub.gateway_service;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator router(RouteLocatorBuilder builder){
        return builder.routes()
                .route("userservice" , route -> route.path("/api/users/**")
                        .uri("lb://user-service"))
                .route("taskservice" , route -> route.path("/api/tasks/**")
                        .uri("lb://task-service"))
                .build();
    }
}
