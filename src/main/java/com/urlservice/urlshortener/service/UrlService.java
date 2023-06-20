package com.urlservice.urlshortener.service;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.function.Supplier;
import io.vavr.control.Try;

@Service
public class UrlService {

    private ApplicationContext context;

    @Autowired
    public UrlService(ApplicationContext context) {
        this.context = context;
    }

    public String shortenUrl(String originalUrl) {
        RateLimiterConfig rateLimiterConfig = (RateLimiterConfig) this.context.getBean("rateLimiterConfig");
        RateLimiter rateLimiter = RateLimiter.of("myRateLimiter", rateLimiterConfig);
        Supplier<String> urlSupplier = RateLimiter.decorateSupplier(rateLimiter, () -> this.executeShortenUrl(originalUrl));
        return Try.ofSupplier(urlSupplier).recover(throwable -> "Rate limit exceeded").get();
    }

    public String executeShortenUrl(String originalUrl) {
        String randomString = RandomStringUtils.randomAlphanumeric(50).concat(originalUrl);
        return RandomStringUtils.random(50, randomString);
    }
}
