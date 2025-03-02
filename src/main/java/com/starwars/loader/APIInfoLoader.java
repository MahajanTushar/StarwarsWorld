package com.starwars.loader;

import com.starwars.domain.Film;
import com.starwars.domain.Films;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class APIInfoLoader {

    @Autowired
    @Qualifier("appWebclient")
    private WebClient webClient;

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
      Films films =  webClient.get().uri("/films").retrieve()
                .bodyToMono(Films.class).block();
      cacheManager.getCache("films").put("films", films);
    }

}
