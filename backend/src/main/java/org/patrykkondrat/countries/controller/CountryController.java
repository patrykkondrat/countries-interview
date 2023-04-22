package org.patrykkondrat.countries.controller;

import org.patrykkondrat.countries.client.GraphQLClient;
import org.patrykkondrat.countries.model.Continent;
import org.patrykkondrat.countries.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private GraphQLClient graphQLClient;

    @PostMapping("/countries")
    public List<Country> getCountriesFromContinent(@RequestParam("continent") String continent) {
        return graphQLClient.getCountriesFromContinent(continent).countries();
    }

}