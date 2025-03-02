package com.starwars.controller;

import com.starwars.domain.Film;
import com.starwars.domain.Films;
import com.starwars.domain.People;
import com.starwars.service.impl.FilmsInfoProvider;
import com.starwars.service.impl.PeoplesInfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

/**
 * This component shall take responsibility of only getting queries handled,
 * no mutations involved
 */

@RestController
public class StarwarsQueryController {


    @Autowired
    private FilmsInfoProvider filmsInfoProvider;

    @Autowired
    private PeoplesInfoProvider peoplesInfoProvider;

    @GetMapping("/films")
    public ResponseEntity<Films> getAllFilmsInfo(){
      return ResponseEntity.ok(filmsInfoProvider.getFilmsInfo());
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable("id") int id){
        return ResponseEntity.ok(filmsInfoProvider.getFilmById(id));
    }

    @GetMapping("/people/{id}")
    public Mono<ResponseEntity<People>> getPeopleById(@PathVariable("id") int id) throws ExecutionException, InterruptedException {
        return  peoplesInfoProvider.getPeopleById(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
