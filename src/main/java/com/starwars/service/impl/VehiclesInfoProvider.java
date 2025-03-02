package com.starwars.service.impl;

import com.starwars.domain.Films;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class VehiclesInfoProvider {

    @Autowired
    private CacheManager cacheManager;



}
