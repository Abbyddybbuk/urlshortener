package com.urlservice.urlshortener.service;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.Random;

@Service
public class UrlService {
    public String shortenUrl(String originalUrl) {
        String randomString = RandomStringUtils.randomAlphanumeric(50).concat(originalUrl);
        return RandomStringUtils.random(50, randomString);
    }
}
