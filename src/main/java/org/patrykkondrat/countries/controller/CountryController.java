package org.patrykkondrat.countries.controller;

import org.patrykkondrat.countries.model.Country;
import org.patrykkondrat.countries.model.CountryDetails;
import org.patrykkondrat.countries.service.GraphqlClientService;
import org.patrykkondrat.countries.service.RestCountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CountryController {

    @Autowired
    private GraphqlClientService graphqlClientService;

    @Autowired
    private RestCountriesService restCountriesService;

    @GetMapping("/countries")
    public List<Country> getCountriesFromContinent(@RequestParam("continent") String continent) {
        return graphqlClientService.getCountriesFromContinent(continent);
    }

    @GetMapping("/countries/random")
    public List<Country> getRandomCountriesFromContinent(@RequestParam("continent") String continent,
                                                         @RequestParam("numRandom") int numRandom) {
        return graphqlClientService.getRandomCountriesFromContinent(continent, numRandom);
    }

    @GetMapping("/countries/details")
    public CountryDetails getCountriesDetails(@RequestParam("countryName") String countryName) {
        return restCountriesService.getCountryDetailsByCountryName(countryName);
    }

    @GetMapping("/countries/details/random")
    public List<CountryDetails> getRandomCountryDetails(@RequestParam("continent") String continent,
                                                        @RequestParam("numRandom") int numRandom) {
        return restCountriesService.getRandomCountryDetails(continent, numRandom);
    }
}
