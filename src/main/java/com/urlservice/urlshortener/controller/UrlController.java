package com.urlservice.urlshortener.controller;

import com.urlservice.urlshortener.pojo.UrlRequestObj;
import com.urlservice.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class UrlController {

    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping(value = "/shortenurl")
    public String shortenUrl(@RequestBody UrlRequestObj originalUrl) {
        return urlService.shortenUrl(originalUrl.getOriginalUrl());
    }
}
