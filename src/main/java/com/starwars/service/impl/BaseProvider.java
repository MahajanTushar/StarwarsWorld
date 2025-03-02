package com.starwars.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public abstract class BaseProvider {

    @Autowired
    protected CacheManager cacheManager;

    @Autowired
    @Qualifier("appWebclient")
    protected WebClient webClient;
}
