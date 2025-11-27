package com.gateway.filter;

import com.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = null;
            System.out.println("👉 INTERCEPTED REQUEST: " + exchange.getRequest().getURI());
            // 1. Check if the request needs validation (e.g., not /auth endpoints)
            if (RouteValidator.isSecured.test( exchange.getRequest())) {

                // 2. Check if header is present
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                // 3. Validate the token
                try {
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    System.out.println("Invalid Access...!");
                    throw new RuntimeException("Unauthorized access to application");
                }
            }
            request = exchange.getRequest()
                    .mutate()
                    .header("X-Gateway-Secret", "Jatin@MicroserviceResult") // Define a hard secret key
                    .build();

            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config {
        // Configuration properties if needed
    }
}