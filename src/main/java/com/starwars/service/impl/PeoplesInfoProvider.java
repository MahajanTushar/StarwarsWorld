package com.starwars.service.impl;

import com.starwars.domain.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class PeoplesInfoProvider extends BaseProvider{

    private final ConcurrentMapCacheManager cacheManager;

    public PeoplesInfoProvider(ConcurrentMapCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Mono<People> getPeopleById(int id) {

        List<People> peopleList = cacheManager.getCache("people").get("people", List.class);
        People people = null;
        if (ObjectUtils.isEmpty(peopleList)) {
            return  webClient.get().uri("/people/"+id).retrieve().bodyToMono(People.class).doOnNext(entry -> {
                cacheManager.getCache("people").put("people", List.of(entry));
            });
        }

//        else {
//            people = peopleList.stream().skip(id - 1).limit(id).findAny().get();
//            if (ObjectUtils.isEmpty(people)) {
//                people = webClient.get().uri("/api/people").retrieve().bodyToMono(People.class).block();
//
//                peopleList.add(people);
//                cacheManager.getCache("people").put("people", peopleList);
//            }
//
//        }
//        return people;
        return Mono.just(null);
    }


}
