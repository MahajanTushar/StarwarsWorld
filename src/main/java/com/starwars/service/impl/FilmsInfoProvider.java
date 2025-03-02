package com.starwars.service.impl;

import com.starwars.domain.Film;
import com.starwars.domain.Films;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FilmsInfoProvider extends BaseProvider {


    public Films getFilmsInfo(){
       return cacheManager.getCache("films").get("films", Films.class);
    }

    public Film getFilmById(int id){

       Films films = cacheManager.getCache("films").get("films", Films.class);
       Optional<Film> film = films.results().stream().skip(id-1).limit(id).findFirst();
       if(film.isPresent()){
           return film.get();
       }else{
          Film newRecord = webClient.get().uri("/api/films/"+id).retrieve().bodyToMono(Film.class).block();
          films.results().add(newRecord);
          cacheManager.getCache("films").put("films", films.results());
          return newRecord;
       }
    }
}
