package org.patrykkondrat.countries.service;

import lombok.RequiredArgsConstructor;
import org.patrykkondrat.countries.client.GraphQLClient;
import org.patrykkondrat.countries.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GraphqlClientService {

    @Autowired
    private final GraphQLClient graphQLClient;

    public List<Country> getCountriesFromContinent(String continent) {
        return graphQLClient.getCountriesFromContinent(continent).countries();
    }

    public List<Country> getRandomCountriesFromContinent(String continent, int numRandom) {
        return graphQLClient.getCountriesFromContinent(continent).getRandomCountries(numRandom);
    }
}
