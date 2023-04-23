package org.patrykkondrat.countries.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public record Continent(
        @JsonProperty("name") String name,
        @JsonProperty("code") String code,
        @JsonProperty("countries") List<Country> countries
) {
    public List<Country> getRandomCountries(int numRandomCountries) {
        if (numRandomCountries < 2 || numRandomCountries > 10) {
            throw new IllegalArgumentException("Number of countries must be between 2 and 10");
        }

        List<Country> result = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numRandomCountries; i++) {
            int randomIndex = random.nextInt(countries.size());
            result.add(countries.get(randomIndex));
        }

        return result;
    }
}

