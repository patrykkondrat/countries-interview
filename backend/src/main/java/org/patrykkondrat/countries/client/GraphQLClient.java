package org.patrykkondrat.countries.client;

import lombok.RequiredArgsConstructor;
import org.patrykkondrat.countries.model.Continent;
import org.patrykkondrat.countries.model.Country;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GraphQLClient {

    private WebClient webClient = WebClient.create();
    private HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder(webClient)
            .url("https://countries.trevorblades.com/graphql")
            .build();

    public Continent getCountriesFromContinent() {

        String document = """
                query Query {
                  continent (code: "EU") {
                    name
                    code
                    countries {
                      name
                      code
                    }
                  }
                }""";

        Mono<Continent> continent = graphQlClient
                .document(document)
                .retrieve("continent")
                .toEntity(Continent.class);

        return continent.block();
    }

    public Map<String, String> availableContinents() {
        Map<String, String> continents = new HashMap<>();
        continents.put("EU", "Europe");
        continents.put("NA", "North America");
        continents.put("SA", "South America");
        continents.put("AF", "Africa");
        continents.put("AS", "Asia");
        continents.put("OC", "Oceania");
        return continents;
    }
}
