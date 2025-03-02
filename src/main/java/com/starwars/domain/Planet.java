package com.starwars.domain;

import lombok.Data;

import java.util.List;

@Data
public class Planet {

        private String name;
        private String rotation_period;
        private String orbital_period;
        private String diameter;
        private String climate;
        private String gravity;
        private String terrain;
        private String surface_water;
        private String population;
        private List<String> residentUrls;
        private List<String> filmUrls;
    }

