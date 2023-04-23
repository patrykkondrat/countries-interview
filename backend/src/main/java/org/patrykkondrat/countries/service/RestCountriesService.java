package org.patrykkondrat.countries.service;

import lombok.RequiredArgsConstructor;
import org.patrykkondrat.countries.client.GraphQLClient;
import org.patrykkondrat.countries.client.RestCountriesClient;
import org.patrykkondrat.countries.model.Country;
import org.patrykkondrat.countries.model.CountryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestCountriesService {

    @Autowired
    private RestCountriesClient restCountriesClient;

    @Autowired
    private GraphqlClientService graphqlClientService;

    public CountryDetails getCountryDetailsByCountryName(String countryName) {
        return restCountriesClient.getCountryDetailsFromRest(countryName);
    }

    public List<CountryDetails> getRandomCountryDetails(String continent, int numRandom) {
        List<Country> countryList = graphqlClientService.getRandomCountriesFromContinent(continent, numRandom);
        List<CountryDetails> countryDetailsList = countryList.stream().map(contry ->
                getCountryDetailsByCountryName(contry.name())).toList();
        return countryDetailsList;
    }
}
