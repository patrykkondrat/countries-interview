package org.patrykkondrat.countries.client;

import lombok.RequiredArgsConstructor;
import org.patrykkondrat.countries.model.Continent;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GraphQLClient {

    private WebClient webClient = WebClient.create();
    private HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder(webClient)
            .url("https://countries.trevorblades.com/graphql")
            .build();

    public Continent getCountriesFromContinent(String continentCode) {
        String finalContinentCode = getValidCode(continentCode);

        String document = String.format("""
                query Query {
                  continent (code: "%s") {
                    name
                    code
                    countries {
                      name
                      code
                    }
                  }
                }""", finalContinentCode);

        Mono<Continent> continent = graphQlClient
                .document(document)
                .retrieve("continent")
                .toEntity(Continent.class);

        return continent.block();
    }
    private String getValidCode(String continentCode) {
        Map<String, String> continents = availableContinents();
        String finalContinentCode = "";
        if (!continents.containsKey(continentCode.toUpperCase()) && !continents.containsValue(continentCode)) {
            throw new IllegalArgumentException("Continent code is not valid");
        }

        if (continentCode.length() == 2) {
            finalContinentCode = continentCode.toUpperCase();
        } else {
            finalContinentCode = continents.entrySet().stream()
                    .filter(entry -> continentCode.equals(entry.getValue()))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);
        }
        return finalContinentCode;
    }

    public Map<String, String> availableContinents() {
        Map<String, String> continents = new HashMap<>();
        continents.put("EU", "Europe");
        continents.put("NA", "North America");
        continents.put("SA", "South America");
        continents.put("AN", "Antarctica");
        continents.put("AF", "Africa");
        continents.put("AS", "Asia");
        continents.put("OC", "Oceania");
        return continents;
    }
}
