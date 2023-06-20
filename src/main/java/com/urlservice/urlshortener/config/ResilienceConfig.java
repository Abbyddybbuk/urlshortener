package com.urlservice.urlshortener.config;



import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

    @Bean("rateLimiterConfig")
    public RateLimiterConfig rateLimiterConfig() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(1) // Maximum number of permits available in a given time period
                .limitRefreshPeriod(Duration.ofSeconds(5)) // Time period in which permits are refreshed
                .timeoutDuration(Duration.ofMillis(100)) // Time to wait for a permit to become available
                .build();

//        return RateLimiter.of("myRateLimiter", config);
        return config;
    }

    @Bean("rateLimiterRegistry")
    public RateLimiterRegistry rateLimiterRegistry(RateLimiterConfig rateLimiterConfig) {
        return RateLimiterRegistry.of(rateLimiterConfig);
    }
}
