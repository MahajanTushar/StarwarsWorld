package com.starwars.domain;
import java.util.List;

public record Film(String title,
                   int episode_id,
                   String opening_crawl,
                   String director,
                   String producer,
                   String release_date,
                   List<String> characters,
                   List<String> planets,
                   List<String> starships,
                   List<String> vehicles,
                   List<String> species,
                   String created,
                   String edited,
                   String url) {
}
